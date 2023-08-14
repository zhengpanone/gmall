

package com.zp.sys.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 字典类型
 *
 * @author 
 */
@Data
@EqualsAndHashCode(callSuper=false)
@Table("sys_dict_type")
public class SysDictTypeEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;
	/**
	 * 字典类型
	 */
	private String dictType;
	/**
	 * 字典名称
	 */
	private String dictName;
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