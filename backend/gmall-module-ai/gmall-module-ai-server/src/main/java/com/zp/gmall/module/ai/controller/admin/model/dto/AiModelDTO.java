package com.zp.gmall.module.ai.controller.admin.model.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup;
import com.zp.gmall.framework.common.validation.ValidateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
@Schema(title = "AI模型DTO", description = "管理后台 - AI模型DTO")
public class AiModelDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @JsonView(ViewGroup.UpdateView.class)
    @Schema(title = "模型ID", description = "模型ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "模型ID不能为空", groups = ValidateGroup.Update.class)
    private String id;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "密钥ID", description = "密钥ID", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "密钥ID不能为空", groups = ValidateGroup.Create.class)
    private String keyId;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "模型名称", description = "模型名称", example = "测试模型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "模型名称不能为空", groups = ValidateGroup.Create.class)
    @Size(max = 32, message = "模型名称长度不能超过32个字符", groups = ValidateGroup.Create.class)
    private String name;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "模型", description = "模型", example = "测试模型", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "模型不能为空", groups = ValidateGroup.Create.class)
    @Size(max = 32, message = "模型长度不能超过32个字符", groups = ValidateGroup.Create.class)
    private String model;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "平台", description = "平台", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "平台不能为空", groups = ValidateGroup.Create.class)
    @Size(max = 32, message = "平台长度不能超过32个字符", groups = ValidateGroup.Create.class)
    private String platform;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "类型", description = "类型", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "类型不能为空", groups = ValidateGroup.Create.class)
    @Size(max = 32, message = "类型长度不能超过32个字符", groups = ValidateGroup.Create.class)
    private String type;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "排序", description = "排序", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "排序不能为空", groups = ValidateGroup.Create.class)
    private Integer sort;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "状态", description = "状态", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "状态不能为空", groups = ValidateGroup.Create.class)
    private Integer status;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "温度", description = "温度", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "温度不能为空", groups = ValidateGroup.Create.class)
    private Double temperature;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "最大令牌数", description = "最大令牌数", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最大令牌数不能为空", groups = ValidateGroup.Create.class)
    private Integer maxTokens;

    @JsonView({ViewGroup.CreateView.class, ViewGroup.UpdateView.class})
    @Schema(title = "最大上下文数", description = "最大上下文数", example = "1", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "最大上下文数不能为空", groups = ValidateGroup.Create.class)
    private Integer maxContexts;
}
