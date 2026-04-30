import type { PageParam } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemDictApi {
  export type DictId = number | string;

  /** 字典状态枚举 */
  export enum DictStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 字典信息 */
  export interface Dict {
    id?: DictId;
    name: string;
    code: string;
    type?: number | string;
    dictName?: string;
    dictCode?: string;
    dictType?: number | string;
    sort?: number;
    status: DictStatusEnum | number;
    createTime?: number | number[] | string;
    remark?: string;
  }

  /** 字典项信息 */
  export interface DictData {
    id?: DictId;
    dictId?: DictId;
    dictType?: string;
    label: string;
    value: string;
    dictLabel?: string;
    dictValue?: string;
    itemCode?: string;
    itemValue?: string;
    sort: number;
    status: number;
    colorType?: string;
    cssClass?: string;
    createTime?: number | number[] | string;
    remark?: string;
  }

  /** 创建字典参数 */
  export interface CreateDictParams {
    code?: string;
    dictCode?: string;
    dictName?: string;
    dictType?: number | string;
    name: string;
    sort?: number;
    type?: number | string;
    status?: number;
    remark?: string;
  }

  /** 更新字典参数 */
  export interface UpdateDictParams extends CreateDictParams {
    id: DictId;
  }

  /** 字典分页查询参数 */
  export interface DictPageParam extends PageParam {
    code?: string;
    dictCode?: string;
    dictName?: string;
    dictType?: number | string;
    name?: string;
    status?: number;
    type?: number | string;
  }

  /** 字典数据分页查询参数 */
  export interface DictDataPageParam extends PageParam {
    dictId?: DictId;
    dictType?: string;
    label?: string;
    status?: number;
    value?: string;
  }

  /** 创建字典数据参数 */
  export interface CreateDictDataParams {
    colorType?: string;
    cssClass?: string;
    dictId?: DictId;
    dictType?: string;
    itemCode?: string;
    itemValue?: string;
    label: string;
    remark?: string;
    sort?: number;
    status?: number;
    value: string;
  }

  /** 更新字典数据参数 */
  export interface UpdateDictDataParams extends CreateDictDataParams {
    id: DictId;
  }
}

function normalizeDictPayload<
  T extends Partial<SystemDictApi.CreateDictParams> & Record<string, any>,
>(data: T) {
  const name = data.name ?? data.dictName;
  const code = data.code ?? data.type ?? data.dictCode ?? data.dictType;

  return {
    ...data,
    code,
    dictCode: data.dictCode ?? code,
    dictName: data.dictName ?? name,
    dictType: data.dictType ?? data.type ?? code,
    name,
  };
}

function normalizeDictDataPayload<
  T extends Partial<SystemDictApi.CreateDictDataParams> & Record<string, any>,
>(data: T) {
  const value = data.value ?? data.dictValue ?? data.itemValue ?? data.itemCode;

  return {
    ...data,
    itemCode: data.itemCode ?? value,
    itemValue: data.itemValue ?? value,
    value,
  };
}

/** 获取字典列表 */
export async function getDictPageList(
  params: Record<string, any> & SystemDictApi.DictPageParam,
) {
  return backendClient.get<Record<string, any>>('/system/admin-api/dict/page', {
    params,
    responseReturn: 'body',
  });
}

/** 获取字典详情 */
export async function getDict(id: SystemDictApi.DictId) {
  return backendClient.get<SystemDictApi.Dict>('/system/admin-api/dict/get', {
    params: { id },
  });
}

/** 创建字典 */
export async function createDict(data: SystemDictApi.CreateDictParams) {
  return backendClient.post(
    '/system/admin-api/dict/create',
    normalizeDictPayload(data),
    { responseReturn: 'body' },
  );
}

/** 更新字典 */
export async function updateDict(data: SystemDictApi.UpdateDictParams) {
  return backendClient.post(
    '/system/admin-api/dict/update',
    normalizeDictPayload(data),
    { responseReturn: 'body' },
  );
}

/** 删除字典 */
export async function deleteDict(ids: SystemDictApi.DictId[]) {
  return backendClient.post(
    '/system/admin-api/dict/delete',
    { ids },
    {
      responseReturn: 'body',
    },
  );
}

/** 获取字典数据项分页 */
export async function getDictDataPageList(
  params: Record<string, any> & SystemDictApi.DictDataPageParam,
) {
  return backendClient.get<Record<string, any>>(
    '/system/admin-api/dict/data/page',
    {
      params,
      responseReturn: 'body',
    },
  );
}

/** 获取字典数据项列表 */
export async function getDictDataList(dictId: SystemDictApi.DictId) {
  return backendClient.get<SystemDictApi.DictData[]>(
    `/system/admin-api/dict/data/${dictId}`,
  );
}

/** 创建字典数据项 */
export async function createDictData(data: SystemDictApi.CreateDictDataParams) {
  return backendClient.post(
    '/system/admin-api/dict/data/create',
    normalizeDictDataPayload(data),
    { responseReturn: 'body' },
  );
}

/** 更新字典数据项 */
export async function updateDictData(data: SystemDictApi.UpdateDictDataParams) {
  return backendClient.post(
    '/system/admin-api/dict/data/update',
    normalizeDictDataPayload(data),
    { responseReturn: 'body' },
  );
}

/** 删除字典数据项 */
export async function deleteDictData(ids: SystemDictApi.DictId[]) {
  return backendClient.post(
    '/system/admin-api/dict/data/delete',
    { ids },
    {
      responseReturn: 'body',
    },
  );
}
