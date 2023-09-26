package com.iot.parse.message;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-26 15:34
 */
public abstract class AbstractMessageParse<T,K> {

    public abstract K parse(T message);

}
