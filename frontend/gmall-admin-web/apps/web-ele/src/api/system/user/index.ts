import type { PageParam } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemUserApi {
  /** 用户状态枚举 */
  export enum UserStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 用户信息 */
  export interface User {
    id?: string;
    username: string;
    nickname: string;
    email?: string;
    mobile?: string;
    avatar?: string;
    deptId?: number;
    status: number | UserStatusEnum;
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
    status?: number;
    remark?: string;
  }

  /** 更新用户参数 */
  export interface UpdateUserParams extends CreateUserParams {
    id: number;
  }

    export interface UserPageParam extends PageParam{
      roleName?: string;
      roleCode?: string;
      roleType?: number;
    }
}

/** 获取用户列表 */
export async function getUserPageList(
params: Record<string, any> & SystemUserApi.UserPageParam,

) {
  return backendClient.get<SystemUserApi.User[]>('/system/admin-api/user/page',
    { params, responseReturn: 'body' });
}

/** 获取用户详情 */
export async function getUser(id: string) {
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
export async function deleteUser(id: string) {
  return backendClient.delete(`/system/admin-api/user/delete/${id}`);
}
