package com.iot.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-22 14:20
 */
@EnableFeignClients(basePackages = "com.iot.api.**")
@SpringBootApplication
@ComponentScan("com.iot.**")
public class AuthApplication {
    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
