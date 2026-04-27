import { backendClient } from '#/api/request';

export namespace SystemUserApi {
  /** 用户状态枚举 */
  export enum UserStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 用户信息 */
  export interface User {
    id?: number;
    username: string;
    nickname: string;
    email?: string;
    mobile?: string;
    avatar?: string;
    deptId?: number;
    status: UserStatusEnum | number;
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
}

/** 获取用户列表 */
export async function getUserList() {
  return backendClient.get<SystemUserApi.User[]>('/system/user/list');
}

/** 获取用户详情 */
export async function getUser(id: number) {
  return backendClient.get<SystemUserApi.User>(`/system/user/${id}`);
}

/** 创建用户 */
export async function createUser(data: SystemUserApi.CreateUserParams) {
  return backendClient.post('/system/user/create', data);
}

/** 更新用户 */
export async function updateUser(data: SystemUserApi.UpdateUserParams) {
  return backendClient.put('/system/user/update', data);
}

/** 删除用户 */
export async function deleteUser(id: number) {
  return backendClient.delete(`/system/user/delete/${id}`);
}
