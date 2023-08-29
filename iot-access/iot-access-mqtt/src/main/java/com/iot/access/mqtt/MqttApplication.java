package com.iot.access.mqtt;

import com.iot.access.mqtt.server.MqttNettyServer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-18 15:27
 */
@SpringBootApplication
@Slf4j
public class MqttApplication {

    @Autowired
    private MqttNettyServer nettyServer;

    public static void main(String[] args) {
        SpringApplicationBuilder springApplicationBuilder = new SpringApplicationBuilder(MqttApplication.class);
        springApplicationBuilder.web(WebApplicationType.NONE).run(args);
    }

    @EventListener
    public void run(ApplicationReadyEvent readyEvent) {
        log.info("mqtt-server 启动....");
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        executorService.execute(() -> nettyServer.run());
        log.info("mqtt-server 启动完成");

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            log.info("进程结束回调,关闭mqtt进程！");
            executorService.shutdown();
        }));
    }
}
