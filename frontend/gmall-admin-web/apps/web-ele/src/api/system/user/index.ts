import type { PageParam, PageResult } from '#/api/core/common';

import { CommonStatusEnum } from '#/api/core/common';
import { backendClient } from '#/api/request';

export namespace SystemUserApi {
  /** 用户信息 */
  export interface User {
    id?: number | string;
    username: string;
    nickname: string;
    email?: string;
    mobile?: string;
    avatar?: string;
    deptId?: number;
    status: CommonStatusEnum | number;
    createTime?: string;
    remark?: string;
  }

  /** 创建用户参数 */
  export interface CreateUserParams {
    username: string;
    nickname: string;
    email?: string;
    mobile?: string;
    deptId?: number;
    status?: CommonStatusEnum | number;
    remark?: string;
  }

  /** 更新用户参数 */
  export interface UpdateUserParams extends CreateUserParams {
    id: number | string;
  }

  export interface UserPageParam extends PageParam {
    deptId?: number | string;
    mobile?: string;
    nickname?: string;
    status?: CommonStatusEnum | number;
    username?: string;
  }
}

/** 获取用户列表 */
export async function getUserPageList(params: Record<string, any> & SystemUserApi.UserPageParam) {
  return backendClient.get<PageResult<SystemUserApi.User>>('/system/admin-api/user/page', {
    params,
    responseReturn: 'body',
  });
}

/** 获取用户详情 */
export async function getUser(id: number | string) {
  return backendClient.get<SystemUserApi.User>(`/system/admin-api/user/${id}`);
}

/** 创建用户 */
export async function createUser(data: SystemUserApi.CreateUserParams) {
  return backendClient.post('/system/admin-api/user/create', data);
}

/** 更新用户 */
export async function updateUser(data: SystemUserApi.UpdateUserParams) {
  return backendClient.put('/system/admin-api/user/update', data);
}

/** 删除用户 */
export async function deleteUser(id: number | string) {
  return backendClient.delete(`/system/admin-api/user/delete/${id}`);
}
