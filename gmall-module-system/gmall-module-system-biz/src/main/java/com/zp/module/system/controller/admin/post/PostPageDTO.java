package com.zp.module.system.controller.admin.post;

import com.zp.framework.common.pojo.PageParam;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 20:01
 * Version : v1.0.0
 * Description:
 */
@Schema(description = "管理后台 - 岗位分页 DTO")
@Data
@EqualsAndHashCode(callSuper = true)
public class PostPageDTO extends PageParam {
    @Schema(description = "岗位编码，模糊匹配", example = "yudao")
    private String code;

    @Schema(description = "岗位名称，模糊匹配", example = "芋道")
    private String name;

    @Schema(description = "展示状态，参见 CommonStatusEnum 枚举类", example = "1")
    private Integer status;

}
