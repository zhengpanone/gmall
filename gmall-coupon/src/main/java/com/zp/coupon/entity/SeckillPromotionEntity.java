package com.zp.coupon.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 秒杀活动
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_seckill_promotion")
public class SeckillPromotionEntity extends BaseEntity {
	private static final long serialVersionUID = 1L;

    /**
     * 活动标题
     */
	private String title;
    /**
     * 开始日期
     */
	private Date startTime;
    /**
     * 结束日期
     */
	private Date endTime;
    /**
     * 上下线状态
     */
	private Integer status;
    /**
     * 创建时间
     */
	private Date createTime;
    /**
     * 创建人
     */
	private String userId;
}