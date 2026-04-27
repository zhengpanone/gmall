import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemConfigApi } from '#/api/system/config';

import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemConfigApi.Config>,
): VxeTableGridColumns<SystemConfigApi.Config> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'name',
      title: $t('system.config.name'),
      width: 150,
    },
    {
      field: 'key',
      title: $t('system.config.key'),
      width: 180,
    },
    {
      field: 'value',
      title: $t('system.config.value'),
      width: 200,
    },
    {
      field: 'type',
      title: $t('system.config.type'),
      width: 100,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.config.status'),
      width: 100,
    },
    {
      field: 'createTime',
      title: $t('system.config.createTime'),
      width: 180,
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
