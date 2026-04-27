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
}

/** 获取部门列表 */
export async function getDeptList() {
  return backendClient.get<SystemDeptApi.Dept[]>('/system/dept/list');
}

/** 获取部门详情 */
export async function getDept(id: number) {
  return backendClient.get<SystemDeptApi.Dept>(`/system/dept/${id}`);
}

/** 创建部门 */
export async function createDept(data: SystemDeptApi.CreateDeptParams) {
  return backendClient.post('/system/dept/create', data);
}

/** 更新部门 */
export async function updateDept(data: SystemDeptApi.UpdateDeptParams) {
  return backendClient.put('/system/dept/update', data);
}

/** 删除部门 */
export async function deleteDept(id: number) {
  return backendClient.delete(`/system/dept/delete/${id}`);
}

/** 获取部门选项列表 */
export async function getDeptOptions() {
  return backendClient.get<SystemDeptApi.Dept[]>('/system/dept/options');
}
