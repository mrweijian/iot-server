package com.iot.access.mqtt.handler.impl;

import com.iot.access.mqtt.handler.PublishMsgInterface;
import com.iot.framework.cons.KafkaTopicConstant;
import com.iot.framework.kafka.KafkaSender;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * 功能描述：设备信息注册
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-26 16:05
 */
@Slf4j
@Component("registerDevicePublishHandler")
public class RegisterDevicePublishHandler implements PublishMsgInterface {


    @Autowired
    private KafkaSender kafkaSender;

    @Override
    public void hand(ChannelHandlerContext ctx, String msgBody) {
        kafkaSender.sendMessage(KafkaTopicConstant.DEVICE_REGISTER_TOPIC, msgBody);
    }
}
