package com.iot.mangement.controller;

import com.iot.common.domain.R;
import com.iot.common.utils.RUtils;
import com.iot.mangement.domain.SysMenuDomain;
import com.iot.mangement.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-25 09:24
 */
@RestController
@RequestMapping("/sysMenu")
public class SysMenuController {

    @Autowired
    private SysMenuService menuService;

    /**
     * 获取当前用户的菜单信息
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public R getMenuList() {
        return RUtils.success(menuService.getMenuList());
    }

    /**
     * 添加菜单信息
     *
     * @param sysMenuDomain
     * @return
     */
    @PostMapping("/addMenuInfo")
    public R addMenuInfo(@RequestBody SysMenuDomain sysMenuDomain) {
        menuService.addMenuInfo(sysMenuDomain);
        return RUtils.success();
    }
}
