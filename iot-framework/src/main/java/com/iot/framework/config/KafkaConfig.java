package com.iot.framework.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-26 15:09
 */
public class KafkaConfig {

    @Bean
    @Primary
    public KafkaTemplate<String, String> defaultKafkaTemplate(ProducerFactory<String, String> producerFactory) {
        return new KafkaTemplate<>(producerFactory);
    }

}
