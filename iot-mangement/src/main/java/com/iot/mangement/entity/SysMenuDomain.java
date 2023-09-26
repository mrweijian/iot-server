package com.iot.mangement.entity;

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

    /**
     * 菜单标题
     */
    private String title;

    /**
     * 是否隐藏
     */
    private boolean hidden;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 菜单主键
     */
    private String menuKey;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 父id
     */
    private Long parentId;

    /**
     * 菜单类型（1-菜单2-按钮）
     */
    private String menuType;

    /**
     * 菜单路径（前端使用）
     */
    private String menuPath;
}
