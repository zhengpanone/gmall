package com.zp.gmall.module.promotion.enums.banner;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:11
 * Version : v1.0.0
 * Description: Banner Position 枚举
 */
@AllArgsConstructor
@Getter
public enum BannerPositionEnum implements Valuable<String> {
    HOME_POSITION("1", "首页"),
    SECKILL_POSITION("2", "秒杀活动页"),
    COMBINATION_POSITION("3", "砍价活动页"),
    DISCOUNT_POSITION("4", "限时折扣页"),
    REWARD_POSITION("5", "满减送页");


    /**
     * 值
     */
    private final String position;

    /**
     * 名称
     */
    private final String name;


    @Override
    public String getValue() {
        return position;
    }
}
