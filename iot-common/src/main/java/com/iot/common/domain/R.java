package com.iot.common.domain;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-17 09:54
 */
@Data
public class R<T> {

    private Integer code;

    private String message;

    private T data;

    public R() {
    }

    public R(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }


}
