/** 分页参数 */
export interface PageParam {
  pageNo: number;
  pageSize: number;
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
