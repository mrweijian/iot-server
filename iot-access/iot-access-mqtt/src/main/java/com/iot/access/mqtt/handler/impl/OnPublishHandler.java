package com.iot.access.mqtt.handler.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.iot.access.mqtt.handler.MqttMsgInterface;
import com.iot.access.mqtt.handler.PublishMsgInterface;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.mqtt.MqttFixedHeader;
import io.netty.handler.codec.mqtt.MqttMessage;
import io.netty.handler.codec.mqtt.MqttMessageFactory;
import io.netty.handler.codec.mqtt.MqttMessageType;
import io.netty.handler.codec.mqtt.MqttPublishMessage;
import io.netty.handler.codec.mqtt.MqttPublishVariableHeader;
import io.netty.handler.codec.mqtt.MqttQoS;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.iot.access.mqtt.constant.DefaultTopicConstant.DEFAULT_REGISTER;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-11 15:10
 */
@Slf4j
@Component
public class OnPublishHandler implements MqttMsgInterface {

    @Autowired
    private Map<String, PublishMsgInterface> publishMsgMap;

    @Override
    public void execute(ChannelHandlerContext ctx, MqttMessage message) {

        MqttPublishMessage msg = (MqttPublishMessage) message;

        String topicName = msg.variableHeader().topicName();
        MqttQoS mqttQoS = msg.fixedHeader().qosLevel();

        String msgBody = msg.payload().toString(CharsetUtil.UTF_8);
        // 去掉换行空格等格式
        JSONObject jsonObject = JSON.parseObject(msgBody);
        msgBody = JSON.toJSONString(jsonObject);
        log.info("主题{}获取到的消息体为：{}", topicName, msgBody);
        if (topicName.startsWith(DEFAULT_REGISTER)) {
            log.info("注册设备基础信息！");
            PublishMsgInterface registerDevicePublishHandler = publishMsgMap.get("registerDevicePublishHandler");
            registerDevicePublishHandler.hand(ctx, msgBody);
        }

        // 回复消息
        ByteBuf byteBuf = Unpooled.wrappedBuffer("{\"msg\":\"你好，已收到！\"}".getBytes());
        MqttMessage mqttMessage = MqttMessageFactory.newMessage(
                new MqttFixedHeader(MqttMessageType.PUBLISH, false, MqttQoS.AT_MOST_ONCE, true, 0),
                new MqttPublishVariableHeader(topicName, -1),
                byteBuf);
        ctx.writeAndFlush(mqttMessage);
    }
}
