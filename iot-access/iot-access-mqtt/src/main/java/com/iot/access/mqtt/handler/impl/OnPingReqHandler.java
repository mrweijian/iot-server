package com.iot.access.mqtt.handler.impl;

import com.iot.access.mqtt.handler.MqttMsgInterface;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;
import lombok.extern.slf4j.Slf4j;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-11 15:11
 */
@Slf4j
public class OnPingReqHandler implements MqttMsgInterface {

    @Override
    public void execute(ChannelHandlerContext ctx, MqttMessage message) {
        log.info("mqtt自带心跳检测：{}", message);
    }
}
