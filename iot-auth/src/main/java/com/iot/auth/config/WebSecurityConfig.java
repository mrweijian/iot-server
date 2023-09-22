package com.iot.auth.config;

import com.iot.auth.exception.AccessDeniedHandlerCust;
import com.iot.auth.exception.AuthenticationEntryPointCust;
import com.iot.auth.service.AuthenticationProviderCustomer;
import com.iot.auth.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-22 14:23
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private AuthenticationProviderCustomer authenticationProviderCustomer;

    @Autowired
    private AuthenticationEntryPointCust entryPointCust;

    @Autowired
    private AccessDeniedHandlerCust accessDeniedHandlerCust;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login")
                .permitAll()//访问/r开始的放行
                .anyRequest()
                .authenticated()//其它请求全部验证
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)// 不需要session
                .and()
                .csrf()
                .disable()
                .exceptionHandling()
                .authenticationEntryPoint(entryPointCust) //处理认证异常
                .accessDeniedHandler(accessDeniedHandlerCust);//处理授权异常

    }

    // 配置登录认证，否则默认从内存中获取用户信息
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());
        auth.authenticationProvider(authenticationProviderCustomer);
    }

}
