import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemRoleApi } from '#/api/system/role';

import { $t } from '#/locales';

const roleTypeOptions = [
  { label: $t('system.role.type1'), value: 1 },
  { label: $t('system.role.type2'), value: 2 },
];

export function useColumns(
  onActionClick: OnActionClickFn<SystemRoleApi.Role>,
): VxeTableGridColumns<SystemRoleApi.Role> {
  return [
    { type: 'checkbox', width: 56, fixed: 'left' },
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'roleCode',
      title: $t('system.role.code'),
      width: 150,
    },
    {
      field: 'roleName',
      title: $t('system.role.name'),
      width: 150,
    },
    {
      cellRender: {
        name: 'CellTag',
        options: roleTypeOptions,
      },
      field: 'roleType',
      title: $t('system.role.type'),
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
          { label: $t('common.enabled'), value: 0 },
          { label: $t('common.disabled'), value: 1 },
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
      title: $t('page.common.operation'),
      width: 150,
    },
  ];
}
