package com.zp.gmall.module.ai.entity.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
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
 * Description: API 密钥
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@TableName("ai_api_key")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AiApiKeyDO extends BaseDO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @TableId
    private String id;
    /**
     * 名称
     */
    private String name;

    /**
     * 密钥
     */
    private String apiKey;

    /**
     * 平台
     * 枚举 {@link AiPlatformEnum}
     */
    private String platform;
    /**
     * API 地址
     */
    private String url;

    /**
     * 状态
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;

    private String remark;
}
