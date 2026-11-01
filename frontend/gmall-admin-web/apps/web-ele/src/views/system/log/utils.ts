export function normalizePageResult<T>(
  response: any,
  currentPage: number,
  currentPageSize: number,
) {
  const data = response ?? {};
  const body =
    data?.data && typeof data.data === 'object' && !Array.isArray(data.data)
      ? data.data
      : data;
  const list = Array.isArray(body)
    ? body
    : (body.list ??
      body.records ??
      body.rows ??
      body.items ??
      data?.list ??
      data?.records ??
      []);
  const total = Number(body.total ?? data.total ?? list.length ?? 0);
  const pageSize = Number(body.pageSize ?? body.size ?? currentPageSize);
  const pageNum = Number(
    body.pageNum ?? body.pageNo ?? body.current ?? currentPage,
  );

  return {
    ...body,
    list: list as T[],
    pageNum,
    pageSize,
    pages: Number(body.pages ?? Math.ceil(total / (pageSize || 1))),
    total,
  };
}

export function getRowText(
  row: null | object | undefined,
  fields: string[],
  fallback = '-',
) {
  if (!row) return fallback;
  const record = row as Record<string, any>;

  for (const field of fields) {
    const value = record[field];
    if (value !== undefined && value !== null && value !== '') {
      return String(value);
    }
  }

  return fallback;
}

export function formatDateTimeValue(value: any, fallback = '-') {
  if (Array.isArray(value)) {
    const [year, month, day, hour = 0, minute = 0, second = 0] = value;
    if (year && month && day) {
      return `${year}-${padNumber(month)}-${padNumber(day)} ${padNumber(hour)}:${padNumber(minute)}:${padNumber(second)}`;
    }
  }

  if (typeof value === 'number') {
    const date = new Date(value);
    if (!Number.isNaN(date.getTime())) {
      return `${date.getFullYear()}-${padNumber(date.getMonth() + 1)}-${padNumber(date.getDate())} ${padNumber(date.getHours())}:${padNumber(date.getMinutes())}:${padNumber(date.getSeconds())}`;
    }
  }

  return value === undefined || value === null || value === ''
    ? fallback
    : String(value);
}

function padNumber(value: number) {
  return String(value).padStart(2, '0');
}
