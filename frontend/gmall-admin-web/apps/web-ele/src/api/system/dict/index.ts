import type { PageParam } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemDictApi {
  /** 字典状态枚举 */
  export enum DictStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 字典信息 */
  export interface Dict {
    id?: string;
    name: string;
    code: string;
    type: number;
    status: DictStatusEnum | number;
    createTime?: string;
    remark?: string;
  }

  /** 字典项信息 */
  export interface DictData {
    id?: string;
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
    id: string;
  }

    /** 字典分页查询参数 */
  export interface DictPageParam extends PageParam {
    name?: string;
    code?: string;
    type?: number;
  }
}



/** 获取字典列表 */
export async function getDictPageList( params: Record<string, any> & SystemDictApi.DictPageParam,) {
  return backendClient.get<SystemDictApi.Dict[]>('/system/admin-api/dict/page', { params, responseReturn: 'body', });
}

/** 获取字典详情 */
export async function getDict(id: string) {
  return backendClient.get<SystemDictApi.Dict>(`/system/admin-api/dict/${id}`);
}

/** 创建字典 */
export async function createDict(data: SystemDictApi.CreateDictParams) {
  return backendClient.post('/system/admin-api/dict/create', data);
}

/** 更新字典 */
export async function updateDict(data: SystemDictApi.UpdateDictParams) {
  return backendClient.put('/system/admin-api/dict/update', data);
}

/** 删除字典 */
export async function deleteDict(ids: Array<number | string>) {
  return backendClient.delete('/system/admin-api/dict/delete', {
    data: { ids },
    responseReturn: 'body',
  });
}

/** 获取字典数据项列表 */
export async function getDictDataList(dictId: string) {
  return backendClient.get<SystemDictApi.DictData[]>(`/system/admin-api/dict/data/${dictId}`);
}
