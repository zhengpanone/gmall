package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 数据字典
 *
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Table("sys_dict_data")
public class SysDictDataEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;
    /**
     * 字典类型ID
     */
    private Long dictTypeId;
    /**
     * 字典标签
     */
    private String dictLabel;
    /**
     * 字典值
     */
    private String dictValue;
    /**
     * 备注
     */
    private String remark;
    /**
     * 排序
     */
    private Integer sort;
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