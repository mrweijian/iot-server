package com.iot.auth.service;

import com.alibaba.fastjson.JSON;
import com.iot.auth.domain.LoginUserDTO;
import com.iot.common.exception.BusinessException;
import com.iot.common.utils.JWTUtils;
import com.iot.common.utils.RUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import static com.iot.common.exception.ExceptionCodeEnum.INVALID_VERIFY_CODE;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-14 14:15
 */
@Service
public class LoginService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public String login(LoginUserDTO dto) {

        if (StringUtils.isEmpty(dto.getUuid())) {
            throw new BusinessException(INVALID_VERIFY_CODE, "验证码uuid为空！");
        }
        // 验证码校验
        String code = stringRedisTemplate.opsForValue().get("verifyCode_" + dto.getUuid());
        if (StringUtils.isEmpty(code)) {
            throw new BusinessException(INVALID_VERIFY_CODE, "验证码为空！");
        }
        if (!code.equals(dto.getVerifyCode())) {
            throw new BusinessException(INVALID_VERIFY_CODE, "验证码错误！");
        }
        //  登录
        return JWTUtils.createToken(JSON.toJSONString(dto));
    }
}
