package com.zp.gmall.module.system.entity.config;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.infra.enums.ConfigTypeEnum;
import lombok.*;

import java.io.Serial;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.entity.config
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@TableName("sys_config")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConfigDO extends BaseDO {
    @Serial
    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 参数分类
     */
    private String category;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值 {@link ConfigTypeEnum}
     */
    private String configValue;

    /**
     * 参数类型
     */
    private String configType;

    /**
     * 备注
     */
    private String remark;

    /**
     * 状态
     */
    private String status;

    /**
     * 是否可见
     */
    private String visible;
}
