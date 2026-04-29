package com.zp.gmall.module.promotion.entity.reward;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.promotion.enums.common.PromotionConditionTypeEnum;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:28
 * Version : v1.0.0
 * Description: 满减送活动
 */
@TableName(value = "promotion_reward_activity")
public class RewardActivityDO extends BaseDO {
    /**
     * 活动编号，主键自增
     */
    @TableId
    private Long id;
    /**
     * 活动标题
     */
    private String name;
    /**
     * 状态
     *
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 开始时间
     */
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    private LocalDateTime endTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 条件类型
     *
     * 枚举 {@link PromotionConditionTypeEnum}
     */
    private Integer conditionType;
    /**
     * 商品范围
     *
     * 枚举 {@link PromotionProductScopeEnum}
     */
    private Integer productScope;
    /**
     * 商品 SPU 编号的数组
     */
    //@TableField(typeHandler = LongListTypeHandler.class)
    private List<Long> productScopeValues;
    /**
     * 优惠规则的数组
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<Rule> rules;

    /**
     * 优惠规则
     */
    @Data
    public static class Rule implements Serializable {

        /**
         * 优惠门槛
         *
         * 1. 满 N 元，单位：分
         * 2. 满 N 件
         */
        private Integer limit;
        /**
         * 优惠价格，单位：分
         */
        private Integer discountPrice;
        /**
         * 是否包邮
         */
        private Boolean freeDelivery;
        /**
         * 赠送的积分
         */
        private Integer point;
        /**
         * 赠送的优惠劵
         *
         * key: 优惠劵模版编号
         * value：对应的优惠券数量
         *
         * 目的：用于订单支付后赠送优惠券
         */
        private Map<Long, Integer> giveCouponTemplateCounts;

    }
}
