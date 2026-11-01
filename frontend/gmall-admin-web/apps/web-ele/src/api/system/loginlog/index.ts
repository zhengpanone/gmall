import type { PageParam, PageResult } from '#/api/core/common';

import { backendClient } from '#/api/request';

export namespace SystemLoginLogApi {
  /** 登录日志信息 */
  export interface LoginLog {
    id?: number | string;
    browser?: string;
    createTime?: number[] | string;
    ipaddr?: string;
    loginLocation?: string;
    loginTime?: number[] | string;
    logType?: number | string;
    msg?: string;
    os?: string;
    result?: number | string;
    status?: number | string;
    userAgent?: string;
    userIp?: string;
    userName?: string;
    username?: string;
  }

  /** 登录日志查询参数 */
  export interface LoginLogQueryParams extends PageParam {
    endTime?: string;
    ipaddr?: string;
    result?: number | string;
    startTime?: string;
    status?: number | string;
    userIp?: string;
    userName?: string;
    username?: string;
  }
}

/** 分页获取登录日志列表 */
export async function getLoginLogPageList(
  params: Record<string, any> & SystemLoginLogApi.LoginLogQueryParams,
) {
  return backendClient.get<PageResult<SystemLoginLogApi.LoginLog>>(
    '/system/admin-api/log/loginLog/page',
    {
      params,
      responseReturn: 'body',
    },
  );
}

/** 获取登录日志详情 */
export async function getLoginLog(id: number | string) {
  return backendClient.get<SystemLoginLogApi.LoginLog>(
    `/system/admin-api/log/loginLog/${id}`,
  );
}

/** 删除登录日志（支持批量） */
export async function deleteLoginLog(ids: Array<number | string>) {
  return backendClient.delete('/system/admin-api/log/loginLog/delete', {
    data: { ids },
  });
}

/** 清空登录日志 */
export async function cleanLoginLog() {
  return backendClient.delete('/system/admin-api/log/loginLog/clean');
}

/** 解锁登录日志 */
export async function unlockLoginLog(info: string) {
  return backendClient.put('/system/admin-api/log/loginLog/unlock', { info });
}
