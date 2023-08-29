package com.iot.mangement.domain.vo;

import com.iot.mangement.domain.SysMenuDomain;
import lombok.Data;

import java.util.List;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-25 10:04
 */
@Data
public class SysMenuDomainVO extends SysMenuDomain {
    private List<SysMenuDomainVO> children;
}
