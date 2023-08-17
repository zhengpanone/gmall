/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 * <p>
 * https://www.renren.io
 * <p>
 * 版权所有，侵权必究！
 */

package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 参数管理
 *
 * @author 
 * @since 1.0.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_params")
public class SysParamsEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 参数编码
     */
    private String paramCode;
    /**
     * 参数值
     */
    private String paramValue;
    /**
     * 类型   0：系统参数   1：非系统参数
     */
    private Integer paramType;
    /**
     * 备注
     */
    private String remark;
    /**
     * 更新者
     */
    private Long updater;
    /**
     * 更新时间
     */
    @Column(onUpdateValue = "now()")
    private Date updateDate;

}