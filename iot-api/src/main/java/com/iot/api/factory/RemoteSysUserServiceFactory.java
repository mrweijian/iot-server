package com.iot.api.factory;

import com.iot.api.service.RemoteSysUserService;
import com.iot.common.domain.R;
import com.iot.common.utils.RUtils;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.web.multipart.MultipartFile;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-31 16:48
 */
public class RemoteSysUserServiceFactory implements FallbackFactory<Object> {
    @Override
    public Object create(Throwable cause) {
        return new RemoteSysUserService() {
            @Override
            public R getMenuList() {
                return RUtils.error("fegin调用错误！");
            }
        };

    }
}
