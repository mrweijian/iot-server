package com.iot.auth.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.iot.auth.domain.LoginUserDTO;
import com.iot.auth.domain.LoginUserDetails;
import com.iot.auth.domain.LoginUserDomain;
import com.iot.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-17 14:38
 */
@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String str) throws UsernameNotFoundException {
        System.out.println(str);

        LoginUserDTO loginUserDTO = JSONObject.parseObject(str, LoginUserDTO.class);
        if (loginUserDTO == null) {
            throw new RuntimeException("参数错误！");
        }
        LambdaQueryWrapper<LoginUserDomain> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LoginUserDomain::getUserName, loginUserDTO.getUserName());
        LoginUserDomain loginUserDomain = userMapper.selectOne(queryWrapper);

        if (loginUserDomain == null) {
            throw new RuntimeException("用户名密码错误！");
        }
        if (!passwordEncoder.matches(loginUserDTO.getPassword(), loginUserDomain.getPassword())) {
            throw new RuntimeException("用户名密码错误！");
        }
        LoginUserDetails loginUserDetails = new LoginUserDetails();
        loginUserDetails.setUsername(loginUserDTO.getUserName());
        loginUserDetails.setPassword(loginUserDTO.getPassword());

        // todo:获取权限
        //loginUserDetails.setAuthorities();
        return loginUserDetails;
    }


}
