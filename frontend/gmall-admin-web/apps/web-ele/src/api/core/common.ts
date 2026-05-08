/** 分页参数 */
export interface PageParam {
  pageNo: number;
  pageSize: number;
}

/** 通用状态，需与后端 CommonStatusEnum 保持一致 */
export enum CommonStatusEnum {
  DISABLED = 0,
  ENABLED = 1,
}

/** 分页结果 */
export interface PageResult<T> {
  list: T[];
  total: number;
  pages: number;
  pageSize: number;
  pageNum: number;
  hasNext: boolean;
  hasPrevious: boolean;
  code: number;
  msg: string;
}

export interface Result<T> {
  code: number;
  data: T;
}
