import type { PageParam, PageResult } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemConfigApi {
  /** 配置状态枚举 */
  export enum ConfigStatusEnum {
    DISABLED = 0,
    ENABLED = 1,
  }

  /** 配置信息 */
  export interface Config {
    id?: number;
    name: string;
    key: string;
    value: string;
    type: number;
    status: ConfigStatusEnum | number;
    createTime?: string;
    remark?: string;
  }

  /** 创建配置参数 */
  export interface CreateConfigParams {
    name: string;
    key: string;
    value: string;
    type?: number;
    status?: number;
    remark?: string;
  }

  /** 更新配置参数 */
  export interface UpdateConfigParams extends CreateConfigParams {
    id: number;
  }

  export interface SysConfigPageParam extends PageParam {
    name?: string;
    key?: string;
    type?: number;
    status?: number;
  }
}

/** 获取配置列表 */
export async function getConfigPageList(
  params: Record<string, any> & SystemConfigApi.SysConfigPageParam,
) {
  return backendClient.get<PageResult<SystemConfigApi.Config>>('/system/admin-api/config/page', {
    params,
    responseReturn: 'body',
  });
}

/** 获取配置详情 */
export async function getConfig(id: number) {
  return backendClient.get<SystemConfigApi.Config>(`/system/admin-api/config/${id}`);
}

/** 创建配置 */
export async function createConfig(data: SystemConfigApi.CreateConfigParams) {
  return backendClient.post('/system/admin-api/config/create', data);
}

/** 更新配置 */
export async function updateConfig(data: SystemConfigApi.UpdateConfigParams) {
  return backendClient.put('/system/admin-api/config/update', data);
}

/** 删除配置 */
export async function deleteConfig(id: number) {
  return backendClient.delete(`/system/admin-api/config/delete/${id}`);
}
