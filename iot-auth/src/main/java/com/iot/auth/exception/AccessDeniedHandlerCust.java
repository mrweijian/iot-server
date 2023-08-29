package com.iot.auth.exception;

import com.iot.auth.util.ResponseWriter;
import com.iot.common.domain.R;
import com.iot.common.utils.RUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-24 09:17
 */
@Component
public class AccessDeniedHandlerCust implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        R error = RUtils.error(e.getMessage());
        ResponseWriter.write(httpServletResponse,error,HttpStatus.UNAUTHORIZED.value());
        e.printStackTrace();
    }

}
