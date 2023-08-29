package com.iot.mangement.domain.dto;

import lombok.Data;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-17 10:01
 */
@Data
public class UserLoginDTO {

    private String userName;

    private String password;

    private String verifyCode;
}
