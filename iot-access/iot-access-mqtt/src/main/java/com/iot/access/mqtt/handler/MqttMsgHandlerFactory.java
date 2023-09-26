package com.iot.access.mqtt.handler;

import com.iot.access.mqtt.handler.impl.OnConnectHandler;
import com.iot.access.mqtt.handler.impl.OnPingReqHandler;
import com.iot.access.mqtt.handler.impl.OnPublishHandler;
import io.netty.handler.codec.mqtt.MqttMessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-06 17:25
 */
@Service
@Slf4j
public class MqttMsgHandlerFactory {
    @Autowired
    private OnConnectHandler onConnectHandler;

    @Autowired
    private OnPublishHandler onPublishHandler;

    @Autowired
    private OnPingReqHandler onPingReqHandler;


    MqttMsgInterface createMsgHandler(MqttMessageType mqttMessageType) {

        switch (mqttMessageType) {
            case CONNECT:
                return onConnectHandler;
            case PUBLISH:
                return onPublishHandler;
            case PINGREQ:
                return onPingReqHandler;
        }
        return null;
    }

}
