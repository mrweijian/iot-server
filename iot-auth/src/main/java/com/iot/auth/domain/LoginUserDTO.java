package com.iot.auth.domain;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-23 14:06
 */
@Data
public class LoginUserDTO {

    private String userName;

    private String password;

    private String verifyCode;

}
