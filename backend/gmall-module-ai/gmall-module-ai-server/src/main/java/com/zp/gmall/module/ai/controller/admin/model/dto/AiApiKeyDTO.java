package com.zp.gmall.module.ai.controller.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup.CreateView;
import com.zp.gmall.framework.common.domain.ViewGroup.UpdateView;
import com.zp.gmall.framework.common.validation.ValidateGroup.Create;
import com.zp.gmall.framework.common.validation.ValidateGroup.Update;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.controller.admin.model.dto
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(title = "AI API密钥DTO", description = "管理后台 - AI API密钥DTO")
public class AiApiKeyDTO {

    @JsonView(UpdateView.class)
    @Schema(title = "API密钥ID", description = "API密钥ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "API密钥ID不能为空", groups = Update.class)
    private String id;

    @JsonView({CreateView.class, UpdateView.class})
    @Schema(title = "API密钥名称", description = "API密钥名称", example = "测试密钥", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "API密钥名称不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "API密钥名称长度不能超过32个字符", groups = {Create.class, Update.class})
    private String apiKeyName;

    @Schema(title = "API密钥", description = "API密钥", example = "<KEY>", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "API密钥不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "API密钥长度不能超过32个字符", groups = {Create.class, Update.class})
    private String apiKey;

    @Schema(title = "平台", description = "平台", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "平台不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "平台长度不能超过32个字符", groups = {Create.class, Update.class})
    private String platform;

    @Schema(title = "URL", description = "URL", example = "https://www.baidu.com", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "URL不能为空", groups = {Create.class, Update.class})
    @Size(max = 255, message = "URL长度不能超过255个字符", groups = {Create.class, Update.class})
    private String url;

    @Schema(title = "状态", description = "状态", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "状态不能为空", groups = {Create.class, Update.class})
    @Size(max = 32, message = "状态长度不能超过32个字符", groups = {Create.class, Update.class})
    private String status;

    @Schema(title = "备注", description = "备注", example = "测试备注")
    @Size(max = 255, message = "备注长度不能超过255个字符", groups = {Create.class, Update.class})
    private String remark;
}
