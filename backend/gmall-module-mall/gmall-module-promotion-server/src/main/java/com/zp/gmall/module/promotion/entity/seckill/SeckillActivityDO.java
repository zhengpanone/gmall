package com.zp.gmall.module.promotion.entity.seckill;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:58
 * Version : v1.0.0
 * Description: 秒杀活动
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("promotion_seckill_activity")
public class SeckillActivityDO extends BaseDO {

    private static final long serialVersionUID = 1L;

    /**
     * 秒杀活动编号
     */
    @TableId
    private String id;
    /**
     * 秒杀活动商品
     */
    private String spuId;
    /**
     * 秒杀活动名称
     */
    private String title;

    /**
     * 备注
     */
    private String remark;
    /**
     * 活动开始时间
     */
    private LocalDateTime startTime;
    /**
     * 活动结束时间
     */
    private LocalDateTime endTime;

    /**
     * 活动状态
     * <p>
     * 枚举 {@link CommonStatusEnum 对应的类}
     */
    private Integer status;

    /**
     * 排序
     */
    private Integer sort;
    /**
     * 秒杀时段 id
     */
    //@TableField(typeHandler = LongListTypeHandler.class)
    private List<String> configIds;

    /**
     * 总限购数量
     */
    private Integer totalLimitCount;
    /**
     * 单次限够数量
     */
    private Integer singleLimitCount;

    /**
     * 秒杀库存(剩余库存秒杀时扣减)
     */
    private Integer stock;
    /**
     * 秒杀总库存
     */
    private Integer totalStock;
}
