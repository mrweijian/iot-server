package com.iot.common.domain;

import lombok.Data;

import java.util.Date;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-23 13:58
 */
@Data
public class JwtTokenDto {
    /**
     * (JWT ID)：编号
     */
    private String jti;
    /**
     * (issuer)：签发人
     */
    private String iss;
    /**
     * (audience)：受众
     */
    private String aud;
    /**
     * (Issued At)：签发时间
     */
    private Date iat;

    /**
     * (expiration time)：过期时间
     */
    private Date exp;


    /**
     * jwt存储的消息体
     */
    private String body;

    /**
     * 判断令牌是否过期
     *
     * @return
     */
    public boolean isExpiration() {
        Date date = new Date();
        if (date.before(this.exp)) {
            return false;
        }
        return true;
    }
}
