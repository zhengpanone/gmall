import type { CreateTenantParams, TenantPageParam, UpdateTenantParams } from './model';

import { backendClient } from '#/api/request';

enum Api {
  dictSync = '/system/admin-api/tenant/syncTenantDict',
  root = '/system/admin-api/tenant',
  tenantCreate = '/system/admin-api/tenant/create',
  tenantDelete = '/system/admin-api/tenant/delete',
  tenantDynamic = '/system/admin-api/tenant/dynamic',
  tenantDynamicClear = '/system/admin-api/tenant/dynamic/clear',
  tenantExport = '/system/admin-api/tenant/export',
  tenantList = '/system/admin-api/tenant/list',
  tenantPageList = '/system/admin-api/tenant/page',
  tenantStatus = '/system/admin-api/tenant/changeStatus',
  tenantSyncPackage = '/system/admin-api/tenant/syncTenantPackage',
  tenantUpdate = '/system/admin-api/tenant/update',
}

export async function getTenantPageList(params: TenantPageParam) {
  return backendClient.get(Api.tenantPageList, { params, responseReturn: 'body' });
}

export async function deleteTenant(ids: Array<number | string>) {
  return backendClient.delete(Api.tenantDelete, {
    data: { ids },
  });
}

export async function createTenant(data: CreateTenantParams) {
  return backendClient.post(Api.tenantCreate, data);
}

export async function updateTenant(data: UpdateTenantParams) {
  return backendClient.put(Api.tenantUpdate, data);
}
