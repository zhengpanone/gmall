import type { PageParam, PageResult, Result } from '#/api/core/common';

import { CommonStatusEnum } from '#/api/core/common';
import { backendClient } from '#/api/request';

export namespace SystemDictApi {
  export type Id = number | string;

  /** 字典类型信息 */
  export interface DictType {
    id?: Id;
    type?: number | string;
    typeName?: string;
    typeCode?: string;
    sort?: number;
    status: CommonStatusEnum | number;
    createTime?: number | number[] | string;
    remark?: string;
  }

  /** 字典项信息 */
  export interface DictData {
    id?: Id;
    typeId?: Id;
    typeCode?: string;
    typeName?: string;
    dataCode?: string;
    dataName?: string;
    parentId?: Id;
    children?: DictData[];
    sort: number;
    status: CommonStatusEnum | number;
    colorType?: string;
    cssClass?: string;
    createTime?: number | number[] | string;
    remark?: string;
  }

  /** 创建字典参数 */
  export interface CreateDictTypeParams {
    typeCode: string;
    typeName: string;
    type: number | string;
    sort?: number;
    status?: CommonStatusEnum | number;
    remark?: string;
  }

  /** 更新字典参数 */
  export interface UpdateDictTypeParams extends CreateDictTypeParams {
    id: Id;
  }

  /** 字典分页查询参数 */
  export interface DictPageParam extends PageParam {
    code?: string;
    dictCode?: string;
    dictName?: string;
    dictType?: number | string;
    name?: string;
    status?: CommonStatusEnum | number;
    type?: number | string;
  }

  /** 字典数据分页查询参数 */
  export interface DictDataPageParam extends PageParam {
    dictId?: Id;
    dictType?: string;
    typeId?: Id;
    typeCode?: string;
    label?: string;
    value?: string;
    dataCode?: string;
    status?: CommonStatusEnum | number;
    dataName?: string;
  }

  /** 字典数据列表查询参数 */
  export interface DictDataListParam {
    typeId?: Id;
    typeCode?: string;
    dataCode?: string;
    status?: CommonStatusEnum | number;
    dataName?: string;
  }

  /** 创建字典数据参数 */
  export interface CreateDictDataParams {
    colorType?: string;
    cssClass?: string;
    dictId?: Id;
    dictType?: string;
    typeId?: Id;
    typeCode?: string;
    typeName?: string;
    itemCode?: string;
    itemValue?: string;
    dataCode?: string;
    dataName?: string;
    parentId?: Id;
    label: string;
    remark?: string;
    sort?: number;
    status?: number;
    value: string;
  }

  /** 更新字典数据参数 */
  export interface UpdateDictDataParams extends CreateDictDataParams {
    id: Id;
  }
}

function normalizeDictPayload<
  T extends Partial<SystemDictApi.CreateDictTypeParams> & Record<string, any>,
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
  const label = data.label ?? data.dataName ?? data.dictLabel ?? data.name;
  const value =
    data.value ?? data.dataCode ?? data.dictValue ?? data.itemValue ?? data.itemCode ?? '';
  const dictId = data.dictId ?? data.typeId;
  const dictType = data.dictType ?? data.typeCode;

  return {
    ...data,
    dataCode: data.dataCode ?? value,
    dataName: data.dataName ?? label,
    dictId,
    dictType,
    label,
    itemCode: data.itemCode ?? value,
    itemValue: data.itemValue ?? value,
    typeCode: data.typeCode ?? dictType,
    typeId: data.typeId ?? dictId,
    value,
  };
}

/**
 * 获取字典类型分页列表
 * @param params - 查询参数，包含分页信息和字典类型筛选条件
 * @returns 返回字典类型分页数据
 */
export async function getDictTypePageList(
  params: Record<string, any> & SystemDictApi.DictPageParam,
) {
  return backendClient.get<PageResult<SystemDictApi.DictType>>('/system/admin-api/dict/type/page', {
    params,
    responseReturn: 'body',
  });
}

/** 获取字典详情 */
export async function getDictType(id: SystemDictApi.Id) {
  return backendClient.get<SystemDictApi.DictType>('/system/admin-api/dict/type/get', {
    params: { id },
  });
}

/** 创建字典 */
export async function createDictType(data: SystemDictApi.CreateDictTypeParams) {
  return backendClient.post('/system/admin-api/dict/type/create', normalizeDictPayload(data));
}

/** 更新字典 */
export async function updateDictType(data: SystemDictApi.UpdateDictTypeParams) {
  return backendClient.post('/system/admin-api/dict/type/update', normalizeDictPayload(data));
}

/** 删除字典 */
export async function deleteDictType(ids: SystemDictApi.Id[]) {
  return backendClient.post('/system/admin-api/dict/delete', { ids });
}

/** 获取字典数据项列表（兼容旧调用） */
export async function getDictDataList(
  params: Record<string, any> & (SystemDictApi.DictDataListParam | SystemDictApi.DictDataPageParam),
) {
  return backendClient.get<Result<SystemDictApi.DictData[]>>('/system/admin-api/dict/data/list', {
    params,
    responseReturn: 'body',
  });
}

/** 创建字典数据项 */
export async function createDictData(data: SystemDictApi.CreateDictDataParams) {
  return backendClient.post('/system/admin-api/dict/data/create', normalizeDictDataPayload(data));
}

/** 更新字典数据项 */
export async function updateDictData(data: SystemDictApi.UpdateDictDataParams) {
  return backendClient.post('/system/admin-api/dict/data/update', normalizeDictDataPayload(data));
}

/** 删除字典数据项 */
export async function deleteDictData(ids: SystemDictApi.Id[]) {
  return backendClient.post('/system/admin-api/dict/data/delete', { ids });
}
