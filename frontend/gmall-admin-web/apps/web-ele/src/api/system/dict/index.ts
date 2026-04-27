import { backendClient } from '#/api/request';

export namespace SystemDictApi {
  /** 字典状态枚举 */
  export enum DictStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 字典信息 */
  export interface Dict {
    id?: number;
    name: string;
    code: string;
    type: number;
    status: DictStatusEnum | number;
    createTime?: string;
    remark?: string;
  }

  /** 字典项信息 */
  export interface DictData {
    id?: number;
    dictId: number;
    label: string;
    value: string;
    sort: number;
    status: number;
    remark?: string;
  }

  /** 创建字典参数 */
  export interface CreateDictParams {
    name: string;
    code: string;
    type?: number;
    status?: number;
    remark?: string;
  }

  /** 更新字典参数 */
  export interface UpdateDictParams extends CreateDictParams {
    id: number;
  }
}

/** 获取字典列表 */
export async function getDictList() {
  return backendClient.get<SystemDictApi.Dict[]>('/system/dict/list');
}

/** 获取字典详情 */
export async function getDict(id: number) {
  return backendClient.get<SystemDictApi.Dict>(`/system/dict/${id}`);
}

/** 创建字典 */
export async function createDict(data: SystemDictApi.CreateDictParams) {
  return backendClient.post('/system/dict/create', data);
}

/** 更新字典 */
export async function updateDict(data: SystemDictApi.UpdateDictParams) {
  return backendClient.put('/system/dict/update', data);
}

/** 删除字典 */
export async function deleteDict(id: number) {
  return backendClient.delete(`/system/dict/delete/${id}`);
}

/** 获取字典数据项列表 */
export async function getDictDataList(dictId: number) {
  return backendClient.get<SystemDictApi.DictData[]>(`/system/dict/data/${dictId}`);
}
