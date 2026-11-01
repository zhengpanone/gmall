import type { UserInfo } from '@vben/types';

import { backendClient } from '#/api/request';

/**
 * 获取用户信息
 */
export async function getUserInfoApi() {
  return backendClient.get<UserInfo>('/system/admin-api/auth/get-permission-info');
}
