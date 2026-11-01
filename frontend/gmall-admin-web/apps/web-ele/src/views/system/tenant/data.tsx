import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { Tenant } from '#/api/system/tenant/model';

import { CommonStatusEnum } from '#/api/core/common';
import { SystemRoleApi } from '#/api/system/role';
import { $t } from '#/locales';

const roleTypeOptions = [
  { label: $t('system.role.type1'), value: SystemRoleApi.RoleTypeEnum.SYSTEM },
  { label: $t('system.role.type2'), value: SystemRoleApi.RoleTypeEnum.CUSTOM },
];

export function useColumns(onActionClick: OnActionClickFn<Tenant>): VxeTableGridColumns<Tenant> {
  return [
    { type: 'checkbox', width: 56, fixed: 'left' },
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'tenantCode',
      title: $t('system.tenant.code'),
      width: 150,
    },
    {
      field: 'tenantName',
      title: $t('system.tenant.name'),
      width: 150,
    },

    {
      field: 'sort',
      title: $t('system.role.sort'),
      width: 100,
    },
    {
      cellRender: {
        name: 'CellTag',
        options: [
          {
            color: 'success',
            label: $t('common.enabled'),
            value: CommonStatusEnum.ENABLED,
          },
          {
            color: 'danger',
            label: $t('common.disabled'),
            value: CommonStatusEnum.DISABLED,
          },
        ],
      },
      field: 'status',
      title: $t('system.role.status'),
      width: 100,
    },
    {
      field: 'createTime',
      title: $t('system.role.createTime'),
      width: 180,
    },
    {
      field: 'remark',
      title: $t('system.role.remark'),
      minWidth: 150,
    },
    {
      align: 'right',
      cellRender: {
        attrs: { onClick: onActionClick },
        name: 'CellOperation',
        options: ['edit', 'delete'],
      },
      field: 'operation',
      fixed: 'right',
      headerAlign: 'center',
      showOverflow: false,
      title: $t('common.actionMessage.operation'),
      width: 150,
    },
  ];
}
