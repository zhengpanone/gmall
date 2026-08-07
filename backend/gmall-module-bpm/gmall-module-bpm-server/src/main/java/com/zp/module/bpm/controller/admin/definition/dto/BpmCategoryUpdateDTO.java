package com.zp.module.bpm.controller.admin.definition.dto;

import lombok.Data;

@Data
public class BpmCategoryUpdateDTO {
    private String id;
    private String name;
    private String code;
    private String description;
    private Integer sort;
}
