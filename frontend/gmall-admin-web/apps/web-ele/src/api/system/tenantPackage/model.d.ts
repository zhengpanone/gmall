import type { PageParam } from '#/api/core/common';
export interface TenantPackage {
  packageName: string;
  packageCode: string;
  id: string;
}

/** 角色分页查询参数 */
export interface TenantPackagePageParam extends PageParam {
  packageName?: string;
  packageCode?: string;
  packageStatus?: string;
}

export interface CreateTenantPackageParams {
  packageName: string;
  packageCode: string;
}

export interface UpdateTenantPackageParams extends CreateTenantPackageParams {
  id: string;
}
