package com.iot.access.mqtt.handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import static com.iot.access.mqtt.constant.DefaultTopicConstant.DEFAULT_REGISTER;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-11 15:10
 */
@Slf4j
public class OnPublishHandler implements MqttMsgInterface{

    @Override
    public void execute(ChannelHandlerContext ctx, MqttMessage message) {

        MqttPublishMessage msg = (MqttPublishMessage) message;

        String topicName = msg.variableHeader().topicName();
        log.info("主题{}接收到发布的消息：{}", topicName, msg);
        MqttQoS mqttQoS = msg.fixedHeader().qosLevel();

        String msgBody = msg.payload().toString(CharsetUtil.UTF_8);
        log.info("主题{}获取到的消息体为：{}", topicName, msgBody);

        if (topicName.startsWith(DEFAULT_REGISTER)) {
            log.info("注册设备基础信息！");
        }
    }
}
