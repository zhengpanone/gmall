import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemUserApi } from '#/api/system/user';

import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemUserApi.User>,
): VxeTableGridColumns<SystemUserApi.User> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'username',
      title: $t('system.user.username'),
      width: 120,
    },
    {
      field: 'nickname',
      title: $t('system.user.nickname'),
      width: 120,
    },
    {
      field: 'email',
      title: $t('system.user.email'),
      width: 180,
    },
    {
      field: 'mobile',
      title: $t('system.user.mobile'),
      width: 130,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.user.status'),
      width: 100,
    },
    {
      field: 'createTime',
      title: $t('system.user.createTime'),
      width: 180,
    },
    {
      field: 'remark',
      title: $t('system.user.remark'),
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
