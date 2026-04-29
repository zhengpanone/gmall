package com.zp.gmall.module.promotion.entity.seckill;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;

import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 01:00
 * Version : v1.0.0
 * Description: 秒杀时段
 */
@Data
public class SeckillConfigDO extends BaseDO {
    /**
     * 编号
     */
    @TableId
    private String id;
    /**
     * 秒杀时段名称
     */
    private String name;
    /**
     * 开始时间点
     */
    private String startTime;
    /**
     * 结束时间点
     */
    private String endTime;
    /**
     * 秒杀轮播图
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> sliderPicUrls;
    /**
     * 状态
     * <p>
     * 枚举 {@link CommonStatusEnum 对应的类}
     */
    private Integer status;
}
