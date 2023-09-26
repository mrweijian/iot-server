package com.iot.parse.message.mqtt;

import com.iot.framework.cons.KafkaTopicConstant;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-26 15:41
 */
public class MqttRegisterMsgParse extends AbstractMqttMsgParse{

    @KafkaListener(topics = {KafkaTopicConstant.DEVICE_REGISTER_TOPIC}, groupId = "default_001")
    public void consumer(ConsumerRecord<?, String> consumerRecord) {
        String value = consumerRecord.value();
        parse(value);
    }

    @Override
    public Object parse(Object message) {
        return null;
    }
}
