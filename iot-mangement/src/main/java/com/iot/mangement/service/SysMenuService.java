package com.iot.mangement.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.mangement.entity.SysMenuDomain;
import com.iot.mangement.entity.vo.SysMenuDomainVO;
import com.iot.mangement.entity.vo.SysMenuTree;
import com.iot.mangement.mapper.SysMenuMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-25 09:26
 */
@Service
public class SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;


    public List<SysMenuDomainVO> getMenuList() {
        LambdaQueryWrapper<SysMenuDomain> queryWrapper = new LambdaQueryWrapper<>();

        List<SysMenuDomain> sysMenuDomains = sysMenuMapper.selectList(queryWrapper);

        List<SysMenuDomainVO> sysMenuDomainVOS = new ArrayList<>();

        for (SysMenuDomain sysMenuDomain : sysMenuDomains) {
            SysMenuDomainVO sysMenuDomainVO = new SysMenuDomainVO();
            BeanUtils.copyProperties(sysMenuDomain, sysMenuDomainVO);
            sysMenuDomainVOS.add(sysMenuDomainVO);
        }
        // 菜单分组
        Map<Long, List<SysMenuDomainVO>> listMap = sysMenuDomainVOS.stream().collect(Collectors.groupingBy(SysMenuDomainVO::getParentId));
        // 设置子菜单
        sysMenuDomainVOS.forEach(vos -> vos.setChildren(listMap.get(vos.getId())));

        return listMap.get(0L);
    }

    public void addMenuInfo(SysMenuDomain sysMenuDomain) {
        sysMenuMapper.insert(sysMenuDomain);
    }

    public List<SysMenuTree> getMenuListTree() {

        List<SysMenuDomainVO> menuList = getMenuList();

        SysMenuTree sysMenuTree = new SysMenuTree();
        sysMenuTree.setLabel("物联协管中心");

        buildMenuTree(menuList, sysMenuTree);


        return Collections.singletonList(sysMenuTree);
    }


    public void buildMenuTree(List<SysMenuDomainVO> menuDomainVOS, SysMenuTree sysMenuTree) {
        List<SysMenuTree> menuTreeList = new ArrayList<>();
        for (SysMenuDomainVO menuDomainVO : menuDomainVOS) {
            SysMenuTree menuTree = new SysMenuTree();
            menuTree.setLabel(menuDomainVO.getTitle());
            menuTreeList.add(menuTree);
            if (!CollectionUtils.isEmpty(menuDomainVO.getChildren())) {
                buildMenuTree(menuDomainVO.getChildren(), menuTree);
            }
        }
        if (!CollectionUtils.isEmpty(menuTreeList)) {
            sysMenuTree.setChildren(menuTreeList);
        }
    }
}
