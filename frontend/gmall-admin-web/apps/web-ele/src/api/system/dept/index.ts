import type { PageParam } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemDeptApi {
  /** 部门状态枚举 */
  export enum DeptStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 部门信息 */
  export interface Dept {
    id?: number;
    name: string;
    parentId: number;
    sort: number;
    leader?: string;
    phone?: string;
    email?: string;
    status: DeptStatusEnum | number;
    createTime?: string;
    remark?: string;
    children?: Dept[];
  }

  /** 创建部门参数 */
  export interface CreateDeptParams {
    name: string;
    parentId?: number;
    sort?: number;
    leader?: string;
    phone?: string;
    email?: string;
    status?: number;
    remark?: string;
  }

  /** 更新部门参数 */
  export interface UpdateDeptParams extends CreateDeptParams {
    id: number;
  }

  /** 部门分页查询参数 */
  export interface DeptPageParam extends PageParam {
    deptName?: string;
    status?: number;
  }
}

/** 获取部门列表 */
export async function getDeptPageList(params: Record<string, any> & SystemDeptApi.DeptPageParam) {
  return backendClient.get<SystemDeptApi.Dept[]>('/system/admin-api/dept/list', {
    params,
    // 角色分页接口返回结构不一定是 { code, data }，拿完整 body 交给页面兼容解析
    responseReturn: 'body',
  });
}

/** 获取部门详情 */
export async function getDept(id: string) {
  return backendClient.get<SystemDeptApi.Dept>(`/system/admin-api/dept/${id}`);
}

/** 创建部门 */
export async function createDept(data: SystemDeptApi.CreateDeptParams) {
  return backendClient.post('/system/admin-api/dept/create', data);
}

/** 更新部门 */
export async function updateDept(data: SystemDeptApi.UpdateDeptParams) {
  return backendClient.put('/system/admin-api/dept/update', data);
}

/** 删除部门 */
export async function deleteDept(ids: Array<number | string>) {
  return backendClient.delete(`/system/admin-api/dept/delete/`, {
    data: { ids },
    responseReturn: 'body',
  });
}

/** 获取部门选项列表 */
export async function getDeptOptions() {
  return backendClient.get<SystemDeptApi.Dept[]>('/system/admin-apim/dept/options');
}
