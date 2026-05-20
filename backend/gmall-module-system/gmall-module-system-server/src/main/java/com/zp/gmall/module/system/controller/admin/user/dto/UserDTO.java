package com.zp.gmall.module.system.controller.admin.user.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.zp.gmall.framework.common.domain.ViewGroup.UpdateView;
import com.zp.gmall.framework.validation.group.CreateGroup;
import com.zp.gmall.framework.validation.group.UpdateGroup;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Set;

@Schema(description = "管理后台 - 用户保存DTO")
@Data
public class UserDTO {

    @JsonView(UpdateView.class)
    @Schema(description = "用户编号", example = "f47ac10b-58cc-4372-a567-0e02b2c3d479")
    @Null(groups = CreateGroup.class, message = "创建时不能传 id")
    @NotNull(message = "更新时 id 不能为空", groups = UpdateGroup.class)
    private String id;


    @Schema(description = "用户账号", type = "string", example = "admin")
    @NotBlank(message = "用户账号不能为空", groups = {CreateGroup.class})
//    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "用户账号由 数字、字母 组成")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{4,30}$", message = "密码需包含大小写字母和数字，长度 8-20 位")
    @Size(min = 4, max = 30, message = "用户账号长度为 4-30 个字符")
    private String username;

    @Schema(description = "用户昵称", type = "string", example = "管理员")
    @Size(max = 30, message = "用户昵称长度不能超过30个字符", groups = {CreateGroup.class, UpdateGroup.class})
    private String nickname;

    @Schema(description = "备注", type = "string", example = "我是管理员")
    private String remark;

    @Schema(description = "岗位编号数组", type = "array", implementation = String.class, example = "[\"f47ac10b-58cc-4372-a567-0e02b2c3d479\"]")
    private Set<String> postIds;

    @Schema(description = "用户邮箱", example = "admin@qq.com")
    @Email(message = "邮箱格式不正确")
    @Size(max = 50, message = "邮箱长度不能超过 50 个字符")
    private String email;

    @Schema(description = "手机号码", example = "15601691300")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;

    @Schema(description = "用户性别，参见 SexEnum 枚举类", example = "1")
    private Integer sex;

    @Schema(description = "用户头像", example = "https://www.iocoder.cn/xxx.png")
    private String avatar;

    @Schema(description = "用户密码", example = "<PASSWORD>")
    private String password;


    private String status;

}
