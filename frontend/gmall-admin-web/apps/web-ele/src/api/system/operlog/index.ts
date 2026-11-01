import type { PageParam, PageResult } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemOperLogApi {
  /** 操作日志信息 */
  export interface OperLog {
    id?: number | string;
    title?: string;
    businessType?: number | string;
    businessTypeName?: string;
    createTime?: number[] | string;
    method?: string;
    requestMethod?: string;
    operatorType?: number | string;
    operName?: string;
    operUrl?: string;
    operIp?: string;
    operLocation?: string;
    operParam?: string;
    jsonResult?: string;
    status?: number | string;
    errorMsg?: string;
    operTime?: number[] | string;
    costTime?: number | string;
    action?: string;
    extra?: string;
    requestUrl?: string;
    subType?: string;
    type?: string;
    userIp?: string;
    userName?: string;
  }

  /** 操作日志查询参数 */
  export interface OperLogQueryParams extends PageParam {
    title?: string;
    businessType?: number | string;
    endTime?: string;
    method?: string;
    operName?: string;
    operUrl?: string;
    startTime?: string;
    status?: number | string;
  }
}

/** 分页获取操作日志列表 */
export async function getOperLogPageList(
  params: Record<string, any> & SystemOperLogApi.OperLogQueryParams,
) {
  return backendClient.get<PageResult<SystemOperLogApi.OperLog>>(
    '/system/admin-api/log/operLog/page',
    {
      params,
      responseReturn: 'body',
    },
  );
}

/** 获取操作日志详情 */
export async function getOperLog(id: number | string) {
  return backendClient.get<SystemOperLogApi.OperLog>(
    `/system/admin-api/log/operLog/${id}`,
  );
}

/** 删除操作日志（支持批量） */
export async function deleteOperLog(ids: Array<number | string>) {
  return backendClient.delete('/system/admin-api/log/operLog/delete', {
    data: { ids },
  });
}

/** 清空操作日志 */
export async function cleanOperLog() {
  return backendClient.delete('/system/admin-api/log/operLog/clean');
}
