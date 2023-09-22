package com.iot.auth.controller;

import com.alibaba.fastjson.JSON;
import com.iot.auth.domain.LoginUserDTO;
import com.iot.auth.service.LoginService;
import com.iot.common.domain.R;
import com.iot.common.utils.JWTUtils;
import com.iot.common.utils.RUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-23 14:03
 */
@RestController
@RequestMapping
public class LoginController {

    @Resource
    private LoginService loginService;

    /**
     * 登录
     *
     * @param dto
     * @return
     */
    @PostMapping("/login")
    public R<?> login(@RequestBody LoginUserDTO dto) {
        //  校验验证码
        return RUtils.success(loginService.login(dto));

    }

}
