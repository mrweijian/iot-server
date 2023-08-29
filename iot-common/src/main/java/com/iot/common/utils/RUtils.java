package com.iot.common.utils;

import com.iot.common.domain.R;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-17 10:45
 */
public class RUtils<T> {

    public static <T> R success(T data) {
        return new R<>(200, "", data);
    }

    public static <T> R error(String message, T data) {
        return new R<>(500, message, data);
    }

    public static <T> R success() {
        return new R<>(200, "", "");
    }

    public static <T> R error(String message) {
        return new R<>(500, message, "");
    }
}
