package com.zp.gmall.module.ai.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.ai.enums.modle.AiModelTypeEnum;
import com.zp.gmall.module.ai.enums.modle.AiPlatformEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.entity.model
 * <p>
 * Description: AI模型 DO
 * 默认模型：{@link #status}为开启，并且{@link #sort}排序第一
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Data
@TableName("ai_model")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiModelDO extends BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 密钥ID {@link AiApiKeyDO#getId()}
     */
    private String keyId;

    /**
     * 模型名称
     */
    private String name;

    /**
     * 模型标志
     */
    private String model;

    /**
     * 平台
     * 枚举 {@link AiPlatformEnum}
     */
    private String platform;

    /**
     * 类型
     * 枚举 {@link AiModelTypeEnum}
     */
    private Integer type;

    /**
     * 排序值
     */
    private Integer sort;

    /**
     * 状态
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    // ===============对话配置====================

    /**
     * 温度参数
     * 用于调整生成回复的随机性和多样性程度：较低的温度值会使输出更收敛于高频词汇，较高的则增加多样性
     */
    private Double temperature;


    /**
     * 单条回复的最大tokens
     */
    private Integer maxTokens;

    /**
     * 上下文的最大Message 数量
     */
    private Integer maxContexts;
}
