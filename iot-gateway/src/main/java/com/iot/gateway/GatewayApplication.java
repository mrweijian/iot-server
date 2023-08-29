package com.iot.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-17 17:29
 */
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {

        // 禁用nacos的logback配置
        System.setProperty("nacos.logging.default.config.enabled","false");

        SpringApplication.run(GatewayApplication.class);
    }
}
