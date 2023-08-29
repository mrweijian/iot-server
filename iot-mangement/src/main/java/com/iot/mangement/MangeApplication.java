package com.iot.mangement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-17 10:51
 */
@SpringBootApplication
public class MangeApplication {

    public static void main(String[] args) {
        // 禁用nacos的logback配置
        System.setProperty("nacos.logging.default.config.enabled","false");

        SpringApplication.run(MangeApplication.class);

        System.out.println("       .__                                                                  \n" +
                "  ____ |  |__   ____   ____    ____             ____   ____   ____    ____  \n" +
                "_/ ___\\|  |  \\_/ __ \\ /    \\  / ___\\   ______  / ___\\ /  _ \\ /    \\  / ___\\ \n" +
                "\\  \\___|   Y  \\  ___/|   |  \\/ /_/  > /_____/ / /_/  >  <_> )   |  \\/ /_/  >\n" +
                " \\___  >___|  /\\___  >___|  /\\___  /          \\___  / \\____/|___|  /\\___  / \n" +
                "     \\/     \\/     \\/     \\//_____/          /_____/             \\//_____/ ");
    }
}
