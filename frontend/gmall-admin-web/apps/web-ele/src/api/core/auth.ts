import { backendClient, baseRequestClient, requestClient } from '#/api/request';

export namespace AuthApi {
  /** 登录接口参数 */
  export interface LoginParams {
    password?: string;
    tenantId?: string;
    username?: string;
    captchaVerification?: string;
    captchaId?: string;
    smsCode?: string;
    socialType?: string;
    socialToken?: string;
  }

  /** 登录接口返回值 */
  export interface LoginResult {
    accessToken: string;
    refreshToken: string;
    userId: number;
    expiresTime: number;
  }

  export interface RefreshTokenResult {
    data: string;
    status: number;
  }
  /** 手机验证码获取接口参数 */
  export interface SmsCodeParams {
    phone: string;
    scene: number;
  }
}

/**
 * 登录
 */
export async function loginApi(data: AuthApi.LoginParams) {
  return backendClient.post<AuthApi.LoginResult>('/system/admin-api/auth/login', data);
}
/** 短信验证码登录 */
export async function smsLoginApi(data: AuthApi.LoginParams) {
  return backendClient.post<AuthApi.LoginResult>('/system/admin-api/auth/sms-login', data);
}
/** 获取登录验证码 */
export async function sendSmsCode(data: AuthApi.LoginParams) {
  return backendClient.post<AuthApi.LoginResult>('/system/admin-api/auth/send-sms-send', data);
}

/**
 * 注册
 */
export async function registerApi(data: AuthApi.LoginParams) {
  return backendClient.post<AuthApi.LoginResult>('/system/admin-api/auth/register', data);
}

/**
 * 刷新accessToken
 */
export async function refreshTokenApi() {
  return baseRequestClient.post<AuthApi.RefreshTokenResult>('/auth/refresh', {
    withCredentials: true,
  });
}

/**
 * 退出登录
 */
export async function logoutApi() {
  return baseRequestClient.post('/auth/logout', {
    withCredentials: true,
  });
}

/**
 * 获取用户权限码
 */
export async function getAccessCodesApi() {
  return requestClient.get<string[]>('/auth/codes');
}
/** 社交快捷登录 */
export async function socialLoginApi(data: AuthApi.LoginParams) {
  return backendClient.post<AuthApi.LoginResult>('/system/admin-api/auth/social-login', data);
}
