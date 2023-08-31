package com.iot.mangement.domain.vo;

import lombok.Data;

import java.util.List;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-29 14:37
 */
@Data
public class SysMenuTree {

    private String label;

    private List<SysMenuTree> children;
}
