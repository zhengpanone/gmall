package com.zp.module.system.controller.admin.tenant;

import com.zp.framework.common.pojo.Result;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.controller.admin.tenant.vo.tenant.TenantSimpleRespVO;
import com.zp.module.system.dal.dataobject.tenant.TenantDO;
import com.zp.module.system.service.tenant.TenantService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.annotation.security.PermitAll;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 11:33
 * Version : v1.0.0
 * Description: 租户
 */
@Tag(name = "管理后台 - 租户")
@RestController
@RequestMapping("/system/tenant")
public class TenantController {

    @Resource
    private TenantService tenantService;


    @GetMapping("/get-by-website")
    @PermitAll
    @Operation(summary = "使用域名，获得租户信息", description = "登录界面，根据用户的域名，获得租户信息")
    @Parameter(name = "website", description = "域名", required = true, example = "www.iocoder.cn")
    public Result<TenantSimpleRespVO> getTenantByWebsite(@RequestParam("website") String website) {
        TenantDO tenant = tenantService.getTenantByWebsite(website);
        return Result.ok(BeanUtils.toBean(tenant, TenantSimpleRespVO.class));
    }
}
