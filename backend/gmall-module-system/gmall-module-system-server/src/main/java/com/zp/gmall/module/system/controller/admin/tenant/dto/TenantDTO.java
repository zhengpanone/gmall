package com.zp.gmall.module.system.controller.admin.tenant.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "租户保存DTO", description = "租户保存传输对象")
public class TenantDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonView(ViewGroup.UpdateView.class)
    @Schema(title = "租户ID", description = "租户ID", example = "租户ID")
    @NotNull(message = "租户ID不能为空", groups = UpdateGroup.class)
    private String id;

    @Schema(title = "租户名称", description = "租户名称", example = "租户名称")
    private String tenantName;

    @Schema(title = "租户编码", description = "租户编码", example = "租户编码")
    private String tenantCode;

    @Schema(title = "联系人名称", description = "联系人名称", example = "联系人名称")
    private String contactName;

    @Schema(title = "联系电话", description = "联系电话", example = "联系电话")
    private String contactPhone;

    @Schema(title = "统一社会信用代码", description = "统一社会信用代码", example = "统一社会信用代码")
    private String licenseNumber;

    @Schema(title = "地址", description = "地址", example = "地址")
    private String address;

    @Schema(title = "域名", description = "域名", example = "域名")
    private String domain;

    @Schema(title = "简介", description = "简介", example = "简介")
    private String intro;

    @Schema(title = "租户状态", description = "租户状态", example = "租户状态")
    private String status;

    @Schema(title = "租户描述", description = "租户描述", example = "租户描述")
    private String description;

    @Schema(title = "域名列表", description = "域名列表", example = "域名列表")
    private List<String> websites;
}
