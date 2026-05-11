import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { TenantPackage } from '#/api/system/tenantPackage/model';

import { CommonStatusEnum } from '#/api/core/common';
import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<TenantPackage>,
): VxeTableGridColumns<TenantPackage> {
  return [
    { type: 'checkbox', width: 56, fixed: 'left' },
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'packageCode',
      title: $t('system.tenantPackage.code'),
      width: 150,
    },
    {
      field: 'packageName',
      title: $t('system.tenantPackage.name'),
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
