import type {
  CreateTenantPackageParams,
  TenantPackagePageParam,
  UpdateTenantPackageParams,
} from './model';

import { backendClient } from '#/api/request';

enum Api {
  tenantPackageCreate = '/system/admin-api/tenantPackage/create',
  tenantPackageDelete = '/system/admin-api/tenantPackage/delete',
  tenantPackageList = '/system/admin-api/tenantPackage/list',
  tenantPackagePageList = '/system/admin-api/tenantPackage/page',
  tenantPackageStatus = '/system/admin-api/tenantPackage/changeStatus',
  tenantPackageUpdate = '/system/admin-api/tenantPackage/update',
}

export async function getTenantPackagePageList(params: TenantPackagePageParam) {
  return backendClient.get(Api.tenantPackagePageList, { params, responseReturn: 'body' });
}

export async function deleteTenantPackage(ids: Array<number | string>) {
  return backendClient.delete(Api.tenantPackageDelete, {
    data: { ids },
  });
}

export async function createTenantPackage(data: CreateTenantPackageParams) {
  return backendClient.post(Api.tenantPackageCreate, data);
}

export async function updateTenantPackage(data: UpdateTenantPackageParams) {
  return backendClient.put(Api.tenantPackageUpdate, data);
}
