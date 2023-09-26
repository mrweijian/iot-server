package com.iot.access.mqtt.handler.impl;

import com.iot.access.mqtt.handler.MqttMsgInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttConnAckVariableHeader;
import io.netty.handler.codec.mqtt.MqttConnectMessage;
import io.netty.handler.codec.mqtt.MqttConnectReturnCode;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.handler.codec.mqtt.MqttVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-11 15:07
 */
@Slf4j
@Component
public class OnConnectHandler implements MqttMsgInterface {

    @Override
    public void execute(ChannelHandlerContext ctx, MqttMessage msg) {
        MqttConnectMessage message = (MqttConnectMessage) msg;
        log.info("连接建立中---------,消息为：{}", message);
        String clientIdentifier = message.payload().clientIdentifier();
        MqttVersion mqttVersion = MqttVersion.fromProtocolNameAndLevel(message.variableHeader().name(), (byte) message.variableHeader().version());
        boolean cleanSession = message.variableHeader().isCleanSession();

        // 返回连接建立成功ack
        MqttMessage ackMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.CONNACK, false, MqttQoS.AT_MOST_ONCE, false, 0),
                new MqttConnAckVariableHeader(MqttConnectReturnCode.CONNECTION_ACCEPTED, !cleanSession),
                null);
        ctx.writeAndFlush(ackMessage);
    }
}
