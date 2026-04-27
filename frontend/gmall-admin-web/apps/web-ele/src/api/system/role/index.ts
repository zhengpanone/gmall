import { backendClient } from '#/api/request';

export namespace SystemRoleApi {
  /** 角色状态枚举 */
  export enum RoleStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 角色信息 */
  export interface Role {
    id?: number;
    name: string;
    code: string;
    sort: number;
    dataScope?: number;
    status: RoleStatusEnum | number;
    createTime?: string;
    remark?: string;
  }

  /** 创建角色参数 */
  export interface CreateRoleParams {
    name: string;
    code: string;
    sort?: number;
    dataScope?: number;
    status?: number;
    remark?: string;
  }

  /** 更新角色参数 */
  export interface UpdateRoleParams extends CreateRoleParams {
    id: number;
  }
}

/** 获取角色列表 */
export async function getRoleList() {
  return backendClient.get<SystemRoleApi.Role[]>('/system/role/list');
}

/** 获取角色详情 */
export async function getRole(id: number) {
  return backendClient.get<SystemRoleApi.Role>(`/system/role/${id}`);
}

/** 创建角色 */
export async function createRole(data: SystemRoleApi.CreateRoleParams) {
  return backendClient.post('/system/role/create', data);
}

/** 更新角色 */
export async function updateRole(data: SystemRoleApi.UpdateRoleParams) {
  return backendClient.put('/system/role/update', data);
}

/** 删除角色 */
export async function deleteRole(id: number) {
  return backendClient.delete(`/system/role/delete/${id}`);
}
