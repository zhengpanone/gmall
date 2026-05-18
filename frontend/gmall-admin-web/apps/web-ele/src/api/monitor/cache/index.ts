import type { CacheInfo } from './model';

import type { Result } from '#/api/core/common';

import { backendClient } from '#/api/request';

export async function redisCacheInfo() {
  return backendClient.get<Result<CacheInfo>>(
    '/infra/admin-api/redis/get-monitor-info',
    {
      responseReturn: 'body',
    },
  );
}
