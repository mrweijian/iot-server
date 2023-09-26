package com.iot.parse.entity;

import java.time.LocalDateTime;
import java.io.Serializable;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author weijian
 * @since 2023-09-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("sys_menu")
public class IotDevice implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 设备唯一序列号
     */
    private String serialNumber;

    /**
     * 设备名称
     */
    private String deviceName;

    /**
     * 设备类型
     */
    private String deviceType;

    /**
     * 说明
     */
    private String description;

    /**
     * 设备状态
     */
    private String state;

    /**
     * 创建时间
     */
    private LocalDateTime createDate;

    /**
     * 创建人id
     */
    private String createId;

    /**
     * 更新时间
     */
    private LocalDateTime updateDate;

    /**
     * 更新人id
     */
    private String updateId;


}
