package com.zp.gmall.module.promotion.entity.seckill;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 秒杀活动场次
 *
 * @author zhengpanone zhengpanone@hotmail.com
 * @since 1.0.0 2022-07-30
 */

@Data
@EqualsAndHashCode(callSuper=false)
@TableName("sms_seckill_session")
public class SeckillSessionEntity extends BaseDO {
	private static final long serialVersionUID = 1L;

    /**
     * 场次名称
     */
	private String name;
    /**
     * 每日开始时间
     */
	private LocalDateTime startTime;
    /**
     * 每日结束时间
     */
	private LocalDateTime endTime;
    /**
     * 启用状态
     */
	private Integer status;
    /**
     * 创建时间
     */
	private LocalDateTime createTime;
}