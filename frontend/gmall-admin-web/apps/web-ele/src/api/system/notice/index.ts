import { CommonStatusEnum } from '#/api/core/common';
import { backendClient } from '#/api/request';

export namespace SystemNoticeApi {
  /** 公告状态枚举 */
  export enum NoticeStatusEnum {
    DISABLED = CommonStatusEnum.DISABLED,
    ENABLED = CommonStatusEnum.ENABLED,
  }

  /** 公告类型枚举 */
  export enum NoticeTypeEnum {
    NOTICE = 1,
    ANNOUNCEMENT = 2,
  }

  /** 公告信息 */
  export interface Notice {
    id?: number;
    title: string;
    content: string;
    type: NoticeTypeEnum | number;
    status: CommonStatusEnum | NoticeStatusEnum | number;
    createTime?: string;
    creator?: string;
    remark?: string;
  }

  /** 创建公告参数 */
  export interface CreateNoticeParams {
    title: string;
    content: string;
    type?: number;
    status?: CommonStatusEnum | number;
    remark?: string;
  }

  /** 更新公告参数 */
  export interface UpdateNoticeParams extends CreateNoticeParams {
    id: number;
  }
}

/** 获取公告列表 */
export async function getNoticeList() {
  return backendClient.get<SystemNoticeApi.Notice[]>('/system/notice/list');
}

/** 获取公告详情 */
export async function getNotice(id: number) {
  return backendClient.get<SystemNoticeApi.Notice>(`/system/notice/${id}`);
}

/** 创建公告 */
export async function createNotice(data: SystemNoticeApi.CreateNoticeParams) {
  return backendClient.post('/system/notice/create', data);
}

/** 更新公告 */
export async function updateNotice(data: SystemNoticeApi.UpdateNoticeParams) {
  return backendClient.put('/system/notice/update', data);
}

/** 删除公告 */
export async function deleteNotice(id: number) {
  return backendClient.delete(`/system/notice/delete/${id}`);
}
