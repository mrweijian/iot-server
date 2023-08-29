package com.iot.auth.util;

import com.alibaba.fastjson.JSON;
import com.iot.common.domain.R;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-24 09:25
 */
public class ResponseWriter {

    public static void write(HttpServletResponse response, R result, int state) {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Access-control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setStatus(state);
        try {
            PrintWriter writer = response.getWriter();
            writer.write(JSON.toJSONString(result));
            writer.flush();
        } catch (Exception ex) {
            throw new RuntimeException("系统错误，请联系管理员！");
        }
    }
}
