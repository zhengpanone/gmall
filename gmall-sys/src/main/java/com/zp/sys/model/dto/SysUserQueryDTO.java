package com.zp.sys.model.dto;

import com.zp.common.entity.PageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "查询用户参数")
public class SysUserQueryDTO extends PageParam implements Serializable {
    private static final long serialVersionUID = 1L;
}
