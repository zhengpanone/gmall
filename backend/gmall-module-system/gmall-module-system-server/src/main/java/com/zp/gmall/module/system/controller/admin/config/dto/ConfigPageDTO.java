package com.zp.gmall.module.system.controller.admin.config.dto;

import com.zp.gmall.framework.common.domain.dto.PageParam;
import lombok.Data;

import java.io.Serial;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.system.controller.admin.config.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-06
 */
@Data
public class ConfigPageDTO extends PageParam {

    @Serial
    private static final long serialVersionUID = 1L;

    private String configName;

    private String configKey;

    private String configType;

    private String status;
}
