import type { PageParam, PageResult } from '#/api/core/common';

import { CommonStatusEnum } from '#/api/core/common';
import { backendClient } from '#/api/request';

export namespace SystemConfigApi {
  export interface Config {
    id?: string;
    category: string;
    configKey: string;
    configName: string;
    configType: string;
    configValue: string;
    createTime?: string;
    remark?: string;
    sort?: number;
    status: CommonStatusEnum | number;
  }

  export interface CreateConfigParams {
    category: string;
    configKey: string;
    configName: string;
    configType: string;
    configValue: string;
    remark?: string;
    status?: CommonStatusEnum | number;
  }

  export interface UpdateConfigParams extends CreateConfigParams {
    id: string;
  }

  export interface SysConfigPageParam extends PageParam {
    configKey?: string;
    configName?: string;
    configType?: string;
    status?: number | string;
  }
}

export async function getConfigPageList(
  params: Record<string, any> & SystemConfigApi.SysConfigPageParam,
) {
  return backendClient.get<PageResult<SystemConfigApi.Config>>(
    '/system/admin-api/config/page',
    {
      params,
      responseReturn: 'body',
    },
  );
}

export async function getConfig(id: string) {
  return backendClient.get<SystemConfigApi.Config>('/system/admin-api/config/get', {
    params: { id },
  });
}

export async function createConfig(data: SystemConfigApi.CreateConfigParams) {
  return backendClient.post('/system/admin-api/config/create', data);
}

export async function updateConfig(data: SystemConfigApi.UpdateConfigParams) {
  return backendClient.put('/system/admin-api/config/update', data);
}

export async function deleteConfig(ids: string[]) {
  return backendClient.delete('/system/admin-api/config/delete', {
    data: { ids },
  });
}
