package com.iot.mangement.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 功能描述：
 *
 * @author weijian
 * @version 1.0
 * @since 2023-08-25 09:27
 */
@Data
@TableName("sys_menu")
public class SysMenuDomain {

    @TableId(type = IdType.AUTO)
    private Long id;

    private String title;

    private boolean hidden;

    private String icon;

    private String menuKey;

    private Date createDate;

    private Date updateDate;

    private String createUser;

    private String updateUser;

    private Long parentId;

    private String menuType;

    private String menuPath;
}
