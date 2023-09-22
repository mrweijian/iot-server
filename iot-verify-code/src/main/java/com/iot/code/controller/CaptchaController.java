package com.iot.code.controller;

import com.iot.code.domain.CaptchaDomain;
import com.iot.common.domain.R;
import com.iot.common.utils.RUtils;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.UUID;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-14 09:29
 */
@RestController
@RequestMapping
public class CaptchaController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/image/stream")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        SpecCaptcha specCaptcha = buildCaptcha();
        specCaptcha.out(response.getOutputStream());
        specCaptcha.toBase64();
    }

    @GetMapping("/image/base64")
    public R<CaptchaDomain> captcha() {

        SpecCaptcha specCaptcha = buildCaptcha();
        String imageBase64 = specCaptcha.toBase64();

        String verifyCode = specCaptcha.text().toLowerCase();
        // 放入redis
        String uuid = UUID.randomUUID().toString().replace("-", "");
        stringRedisTemplate.opsForValue().set("verifyCode_" + uuid, verifyCode);

        System.out.println("uuid: " + uuid + "code: " + verifyCode);
        return RUtils.success(CaptchaDomain.builder().uuid(uuid).imageBase64(imageBase64).build());
    }

    private SpecCaptcha buildCaptcha() {
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, 32));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);


        return specCaptcha;
    }
}
