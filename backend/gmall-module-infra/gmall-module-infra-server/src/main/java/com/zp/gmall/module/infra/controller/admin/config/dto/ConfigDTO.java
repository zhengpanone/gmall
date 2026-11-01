package com.zp.gmall.module.infra.controller.admin.config.dto;

import lombok.Data;

@Data
public class ConfigDTO {
    private String id;
    private String category;
    private String name;
    private String configKey;
    private String value;
    private Integer type;
    private Boolean visible;
    private String remark;
}
