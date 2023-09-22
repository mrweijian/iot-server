package com.iot.code.domain;

import lombok.Builder;
import lombok.Data;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-14 13:52
 */
@Data
@Builder
public class CaptchaDomain {

    private String uuid;

    private String imageBase64;
}
