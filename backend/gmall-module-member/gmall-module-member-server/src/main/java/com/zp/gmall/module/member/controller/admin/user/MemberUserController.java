package com.zp.gmall.module.member.controller.admin.user;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.member.controller.admin.user.dto.MemberUserDTO;
import com.zp.gmall.module.member.entity.user.MemberUserDO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:04
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 会员用户")
@RestController
@RequestMapping("/user")
@Validated
public class MemberUserController {

    @PutMapping("/update")
    @Operation(summary = "更新会员用户")
    public Result<?> updateUser(@Valid @RequestBody MemberUserDTO memberUserDTO) {
        //memberUserService.updateUser(updateReqVO);
        return Result.ok();
    }
}
