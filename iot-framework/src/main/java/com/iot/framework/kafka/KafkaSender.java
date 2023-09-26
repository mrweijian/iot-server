package com.iot.framework.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-26 16:08
 */
public class KafkaSender {

    @Autowired
    private KafkaTemplate<String, String> stringKafkaTemplate;

    public void sendMessage(String topic, String message) {
        stringKafkaTemplate.send(topic, message);
    }
}
