package com.iot.common.exception;

import com.iot.common.domain.R;
import com.iot.common.utils.RUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-09-14 14:36
 */
@ResponseStatus(value = HttpStatus.OK)
@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(value = BusinessException.class)
    @ResponseBody
    public R handleException(BusinessException e) {
        return RUtils.error(e.getCodeEnum().getCode(), e.getMessage());
    }


    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public R handleException(Exception e) {
        return RUtils.error("系统异常，联系管理员！");
    }
}
