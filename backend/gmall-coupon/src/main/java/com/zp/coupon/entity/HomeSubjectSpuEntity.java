package com.zp.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 专题商品
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_home_subject_spu")
public class HomeSubjectSpuEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 专题名字
     */
	private String name;
    /**
     * 专题id
     */
	private Long subjectId;
    /**
     * spu_id
     */
	private Long spuId;
    /**
     * 排序
     */
	private Integer sort;
}