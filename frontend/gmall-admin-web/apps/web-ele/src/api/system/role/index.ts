import { backendClient } from '#/api/request';

export namespace SystemRoleApi {
  /** 角色状态枚举 */
  export enum RoleStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 角色类型枚举 */
  export enum RoleTypeEnum {
    SYSTEM = 1,
    CUSTOM = 2,
  }

  /** 角色信息 */
  export interface Role {
    id?: number | string;
    roleName: string;
    roleCode: string;
    roleType: number;
    sort: number;
    status: RoleStatusEnum;
    statusName?: string;
    roleTypeName?: string;
    createTime?: number[] | string;
    updateTime?: number[] | string;
    remark?: string;
  }

  /** 创建角色参数 */
  export interface CreateRoleParams {
    roleName: string;
    roleCode: string;
    roleType?: number;
    sort?: number;
    status?: number;
    remark?: string;
  }

  /** 更新角色参数 */
  export interface UpdateRoleParams extends CreateRoleParams {
    id: number | string;
  }

  /** 分页参数 */
  export interface PageParam {
    pageNo: number;
    pageSize: number;
  }

  /** 角色分页查询参数 */
  export interface RolePageParam extends PageParam {
    roleName?: string;
    roleCode?: string;
    roleType?: number;
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
}

/** 分页获取角色列表 */
export async function getRolePageList(
  params: Record<string, any> & SystemRoleApi.RolePageParam,
) {
  return backendClient.get<Record<string, any>>(
    '/system/admin-api/role/page',
    {
      params,
      // 角色分页接口返回结构不一定是 { code, data }，拿完整 body 交给页面兼容解析
      responseReturn: 'body',
    },
  );
}

/** 获取角色详情 */
export async function getRole(id: number | string) {
  return backendClient.get<SystemRoleApi.Role>(`/system/admin-api/role/${id}`);
}

/** 创建角色 */
export async function createRole(data: SystemRoleApi.CreateRoleParams) {
  return backendClient.post('/system/admin-api/role/create', data, {
    responseReturn: 'body',
  });
}

/** 更新角色 */
export async function updateRole(data: SystemRoleApi.UpdateRoleParams) {
  return backendClient.put('/system/admin-api/role/update', data, {
    responseReturn: 'body',
  });
}

/** 删除角色（支持批量） */
export async function deleteRole(ids: Array<number | string>) {
  return backendClient.delete('/system/admin-api/role/delete', {
    data: {ids},
    responseReturn: 'body',
  });
}
