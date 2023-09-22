package com.iot.common.exception;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-14 14:29
 */

public class BusinessException extends RuntimeException {

    @Getter
    private ExceptionCodeEnum codeEnum;

    @Getter
    private String message;

    public BusinessException(String message) {
        super(message);
    }


    public BusinessException(ExceptionCodeEnum codeEnum, String message) {
        this.codeEnum = codeEnum;
        this.message = message;
    }
}
