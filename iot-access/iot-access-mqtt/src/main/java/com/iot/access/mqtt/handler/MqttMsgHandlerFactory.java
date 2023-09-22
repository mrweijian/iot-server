package com.iot.access.mqtt.handler;

import com.iot.access.mqtt.handler.impl.OnConnectHandler;
import com.iot.access.mqtt.handler.impl.OnPingReqHandler;
import com.iot.access.mqtt.handler.impl.OnPublishHandler;
import io.netty.handler.codec.mqtt.MqttMessageType;
import lombok.extern.slf4j.Slf4j;
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

    MqttMsgInterface createMsgHandler(MqttMessageType mqttMessageType) {

        switch (mqttMessageType) {
            case CONNECT:
                return new OnConnectHandler();
            case PUBLISH:
                return new OnPublishHandler();
            case PINGREQ:
                return new OnPingReqHandler();
        }
        return null;
    }

}
