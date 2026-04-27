import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemDictApi } from '#/api/system/dict';

import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemDictApi.Dict>,
): VxeTableGridColumns<SystemDictApi.Dict> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'name',
      title: $t('system.dict.name'),
      width: 150,
    },
    {
      field: 'code',
      title: $t('system.dict.code'),
      width: 150,
    },
    {
      field: 'type',
      title: $t('system.dict.type'),
      width: 100,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.dict.status'),
      width: 100,
    },
    {
      field: 'createTime',
      title: $t('system.dict.createTime'),
      width: 180,
    },
    {
      field: 'remark',
      title: $t('system.dict.remark'),
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
