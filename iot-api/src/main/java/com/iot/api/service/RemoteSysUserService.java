package com.iot.api.service;

import com.iot.api.domain.ServiceNameConstants;
import com.iot.api.factory.RemoteSysUserServiceFactory;
import com.iot.common.domain.R;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import sun.security.util.SecurityConstants;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-31 16:20
 */
@FeignClient(contextId = "remoteSysUserService",
        value = ServiceNameConstants.IOT_MANGE_SERVICE,
        fallbackFactory = RemoteSysUserServiceFactory.class)
public interface RemoteSysUserService {

    @GetMapping("/mange/sysMenu/getMenuList")
    R getMenuList();

}
