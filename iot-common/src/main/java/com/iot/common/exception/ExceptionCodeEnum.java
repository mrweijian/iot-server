package com.iot.common.exception;

import lombok.Getter;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-14 14:59
 */
public enum ExceptionCodeEnum {

    INVALID_VERIFY_CODE("验证码错误！", 400);

    @Getter
    private String msg;

    @Getter
    private int code;

    ExceptionCodeEnum(String msg, int code) {
        this.code = code;
        this.msg = msg;
    }


}
