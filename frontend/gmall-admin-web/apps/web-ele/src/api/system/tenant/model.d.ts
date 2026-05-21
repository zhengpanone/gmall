import type { PageParam } from '#/api/core/common';
export interface Tenant {
  tenantName: string;
  accountCount: number;
  address?: string;
  companyName: string;
  contactPhone: string;
  contactUserName: string;
  domain?: string;
  expireTime?: string;
  id: string;
  intro: string;
  licenseNumber?: any;
  packageId: string;
  remark?: string;
  status: string;
  tenantId: string;
}

export interface TenantByWebsite {
  description?: string;
  id: string;
  status?: string;
  tenantCode?: string;
  tenantName: string;
  websites?: string[];
}

/** 角色分页查询参数 */
export interface TenantPageParam extends PageParam {
  tenantName?: string;
  tenantCode?: string;
}

export interface CreateTenantParams {
  tenantName: string;
  accountCount: number;
  address?: string;
  companyName: string;
  contactPhone: string;
  contactUserName: string;
  domain?: string;
  expireTime?: string;
  intro: string;
  licenseNumber?: any;
  packageId: string;
  remark?: string;
  status: string;
  tenantId: string;
}

export interface UpdateTenantParams extends CreateTenantParams {
  id: string;
}
