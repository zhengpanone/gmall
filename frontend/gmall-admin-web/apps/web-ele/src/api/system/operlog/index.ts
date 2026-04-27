import { backendClient } from '#/api/request';

export namespace SystemOperLogApi {
  /** 操作日志信息 */
  export interface OperLog {
    id?: number;
    title: string;
    businessType: number;
    method: string;
    requestMethod: string;
    operatorType: number;
    operName: string;
    operIp: string;
    operLocation: string;
    operParam: string;
    jsonResult: string;
    status: number;
    errorMsg: string;
    operTime: string;
    costTime: number;
  }

  /** 操作日志查询参数 */
  export interface OperLogQueryParams {
    title?: string;
    businessType?: number;
    operName?: string;
    status?: number;
    startTime?: string;
    endTime?: string;
    pageNum?: number;
    pageSize?: number;
  }
}

/** 获取操作日志列表 */
export async function getOperLogList() {
  return backendClient.get<SystemOperLogApi.OperLog[]>(
    '/system/operlog/list',
  );
}

/** 获取操作日志详情 */
export async function getOperLog(id: number) {
  return backendClient.get<SystemOperLogApi.OperLog>(`/system/operlog/${id}`);
}

/** 删除操作日志 */
export async function deleteOperLog(id: number) {
  return backendClient.delete(`/system/operlog/delete/${id}`);
}

/** 清空操作日志 */
export async function cleanOperLog() {
  return backendClient.delete('/system/operlog/clean');
}
