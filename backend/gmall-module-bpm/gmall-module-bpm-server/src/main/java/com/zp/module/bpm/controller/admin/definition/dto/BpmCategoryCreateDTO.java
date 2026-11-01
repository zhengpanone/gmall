package com.zp.module.bpm.controller.admin.definition.dto;

import lombok.Data;

@Data
public class BpmCategoryCreateDTO {
    private String name;
    private String code;
    private String description;
    private Integer sort;
}
