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
}

/** 获取配置列表 */
export async function getConfigList() {
  return backendClient.get<SystemConfigApi.Config[]>('/system/config/list');
}

/** 获取配置详情 */
export async function getConfig(id: number) {
  return backendClient.get<SystemConfigApi.Config>(`/system/config/${id}`);
}

/** 创建配置 */
export async function createConfig(data: SystemConfigApi.CreateConfigParams) {
  return backendClient.post('/system/config/create', data);
}

/** 更新配置 */
export async function updateConfig(data: SystemConfigApi.UpdateConfigParams) {
  return backendClient.put('/system/config/update', data);
}

/** 删除配置 */
export async function deleteConfig(id: number) {
  return backendClient.delete(`/system/config/delete/${id}`);
}
