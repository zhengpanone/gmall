import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemRoleApi } from '#/api/system/role';

import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemRoleApi.Role>,
): VxeTableGridColumns<SystemRoleApi.Role> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'name',
      title: $t('system.role.name'),
      width: 150,
    },
    {
      field: 'code',
      title: $t('system.role.code'),
      width: 150,
    },
    {
      field: 'sort',
      title: $t('system.role.sort'),
      width: 100,
    },
    {
      cellRender: { name: 'CellTag' },
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
      title: $t('page.common.operation'),
      width: 150,
    },
  ];
}
