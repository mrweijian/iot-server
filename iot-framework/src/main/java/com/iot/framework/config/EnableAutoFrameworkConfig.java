package com.iot.framework.config;

import com.iot.framework.kafka.KafkaSender;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-26 15:21
 */
@Configuration
@Import({KafkaConfig.class, KafkaSender.class})
public class EnableAutoFrameworkConfig {
}
