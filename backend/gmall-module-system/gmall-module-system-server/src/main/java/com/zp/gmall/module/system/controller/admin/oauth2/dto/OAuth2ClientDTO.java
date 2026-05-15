package com.zp.gmall.module.system.controller.admin.oauth2.dto;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-15
 */
@Data
public class OAuth2ClientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private String clientId;

    private String clientSecret;

    private String clientName;

    private String clientLogo;

    private String clientDescription;

    private String redirectUri;

    private String scope;

    private String grantType;

    private String accessTokenValidity;

    private String refreshTokenValidity;
}
