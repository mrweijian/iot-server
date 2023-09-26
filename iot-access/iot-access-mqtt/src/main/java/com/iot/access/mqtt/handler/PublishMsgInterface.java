package com.iot.access.mqtt.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttMessage;

public interface PublishMsgInterface {

    void hand(ChannelHandlerContext ctx, String msgBody);

}
