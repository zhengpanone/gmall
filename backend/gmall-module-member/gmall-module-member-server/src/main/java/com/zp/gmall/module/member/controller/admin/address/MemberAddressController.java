package com.zp.gmall.module.member.controller.admin.address;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.member.controller.admin.address.dto.MemberAddressDTO;
import com.zp.gmall.module.member.service.address.IMemberAddressService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Tag(name = "管理后台 - 会员地址")
@RestController
@RequestMapping("/address")
@Validated
@RequiredArgsConstructor
public class MemberAddressController {

    private final IMemberAddressService memberAddressService;


    public Result<?> createAddress(@Valid @RequestBody MemberAddressDTO memberAddressDTO) {
        return Result.ok();
    }
}
