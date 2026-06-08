import { backendClient } from '../request';

/** 分页参数 */
export interface PageParam {
  pageNo: number;
  pageSize: number;
}

/** 通用状态，需与后端 CommonStatusEnum 保持一致 */
export enum CommonStatusEnum {
  DISABLED = 0,
  ENABLED = 1,
}

/** 分页结果 */
export interface PageResult<T> {
  list: T[];
  total: number;
  pages: number;
  pageSize: number;
  pageNum: number;
  hasNext: boolean;
  hasPrevious: boolean;
  code: number;
  msg: string;
}

export interface Result<T> {
  code: number;
  data: T;
}
export interface ConfigResult {
  category: string;
  configKey: string;
  configValue: string;
}

export interface ConfigResult1 {
  CAPTCHA_ENABLE: ConfigResult;
  [key: string]: ConfigResult;
}

/**
 * 获取系统配置
 */
export async function getAnonymousConfigListApi(data: Array<string>) {
  return backendClient.post<Record<string, ConfigResult>>(
    '/system/admin-api/config/anonymous/getKeys',
    data,
  );
}

/** 校验验证码 */
export async function checkCaptcha(data: any) {
  return backendClient.post('/system/captcha/check', data);
}

/** 获取验证码 */
export async function getCaptcha(data: any) {
  return backendClient.post('/system/captcha/get', data);
}
