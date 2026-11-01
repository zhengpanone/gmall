package com.zp.gmall.framework.common.biz.oauth2;


import com.zp.gmall.framework.common.biz.oauth2.dto.OAuth2AccessTokenDTO;
import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenCheckVO;
import com.zp.gmall.framework.common.biz.oauth2.vo.OAuth2AccessTokenVO;
import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.framework.common.enums.RpcConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(contextId = "oAuth2TokenApi", name = RpcConstants.SYSTEM_NAME, path = RpcConstants.SYSTEM_CONTEXT_PATH)
@Tag(name = "RPC 服务 - OAuth2.0 令牌")
public interface OAuth2TokenCommonApi {

    String PREFIX = RpcConstants.SYSTEM_PREFIX + "/oauth2/token";

    /**
     * 校验 Token 的 URL 地址，主要是提供给 Gateway 使用
     */
    @SuppressWarnings("HttpUrlsUsage")
    String URL_CHECK = "http://" + RpcConstants.SYSTEM_NAME + PREFIX + "/check";


    @PostMapping(PREFIX + "/create")
    @Operation(summary = "创建访问令牌")
    Result<OAuth2AccessTokenVO> createAccessToken(@Valid @RequestBody OAuth2AccessTokenDTO reqDTO);


    @GetMapping(PREFIX + "/check")
    @Operation(summary = "校验访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    Result<OAuth2AccessTokenCheckVO> checkAccessToken(@RequestParam("accessToken") String accessToken);


    @DeleteMapping(PREFIX + "/remove")
    @Operation(summary = "移除访问令牌")
    @Parameter(name = "accessToken", description = "访问令牌", required = true, example = "tudou")
    Result<OAuth2AccessTokenVO> removeAccessToken(@RequestParam("accessToken") String accessToken);


    @PutMapping(PREFIX + "/refresh")
    @Operation(summary = "刷新访问令牌")
    @Parameters({
            @Parameter(name = "refreshToken", description = "刷新令牌", required = true, example = "haha"),
            @Parameter(name = "clientId", description = "客户端编号", required = true, example = "yudaoyuanma")
    })
    Result<OAuth2AccessTokenVO> refreshAccessToken(@RequestParam("refreshToken") String refreshToken,
                                                   @RequestParam("clientId") String clientId);

}
