import { backendClient } from '#/api/request';

export namespace SystemLoginLogApi {
  /** 登录日志信息 */
  export interface LoginLog {
    id?: number;
    userName: string;
    ipaddr: string;
    loginLocation: string;
    browser: string;
    os: string;
    status: number;
    msg: string;
    loginTime: string;
  }

  /** 登录日志查询参数 */
  export interface LoginLogQueryParams {
    userName?: string;
    ipaddr?: string;
    status?: number;
    startTime?: string;
    endTime?: string;
    pageNum?: number;
    pageSize?: number;
  }
}

/** 获取登录日志列表 */
export async function getLoginLogList() {
  return backendClient.get<SystemLoginLogApi.LoginLog[]>('/system/loginlog/list');
}

/** 获取登录日志详情 */
export async function getLoginLog(id: number) {
  return backendClient.get<SystemLoginLogApi.LoginLog>(
    `/system/loginlog/${id}`,
  );
}

/** 删除登录日志 */
export async function deleteLoginLog(id: number) {
  return backendClient.delete(`/system/loginlog/delete/${id}`);
}

/** 清空登录日志 */
export async function cleanLoginLog() {
  return backendClient.delete('/system/loginlog/clean');
}

/** 解锁登录日志 */
export async function unlockLoginLog(info: string) {
  return backendClient.put('/system/loginlog/unlock', { info });
}
