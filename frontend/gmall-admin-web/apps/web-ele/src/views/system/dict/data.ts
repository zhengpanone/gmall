import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemDictApi } from '#/api/system/dict';

import { $t } from '#/locales';

export function useDictTypeColumns(
  onActionClick: OnActionClickFn<SystemDictApi.Dict>,
): VxeTableGridColumns<SystemDictApi.Dict> {
  return [
    { type: 'checkbox', width: 46 },
    {
      field: 'name',
      title: $t('system.dict.name'),
      minWidth: 120,
    },
    {
      field: 'type',
      title: $t('system.dict.type'),
      minWidth: 150,
    },
    {
      field: 'remark',
      minWidth: 150,
      title: $t('system.dict.remark'),
    },
    {
      field: 'createTime',
      minWidth: 160,
      title: $t('system.dict.createTime'),
      formatter: ({ row }) => String(row.createTime ?? ''),
    },
    {
      align: 'right',
      cellRender: {
        attrs: { onClick: onActionClick },
        name: 'CellOperation',
        options: [
          'edit',
          { code: 'delete', danger: true, text: $t('common.delete') },
        ],
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

export function useDictDataColumns(
  onActionClick: OnActionClickFn<SystemDictApi.DictData>,
): VxeTableGridColumns<SystemDictApi.DictData> {
  return [
    { type: 'checkbox', width: 46 },
    {
      field: 'label',
      minWidth: 120,
      slots: { default: 'dictDataLabel' },
      title: $t('system.dict.dataLabel'),
    },
    {
      field: 'value',
      minWidth: 120,
      title: $t('system.dict.dataValue'),
    },
    {
      field: 'sort',
      title: $t('system.dict.dataSort'),
      width: 100,
    },
    {
      field: 'remark',
      minWidth: 150,
      title: $t('system.dict.remark'),
    },
    {
      field: 'createTime',
      minWidth: 160,
      title: $t('system.dict.createTime'),
    },
    {
      align: 'right',
      cellRender: {
        attrs: { nameField: 'label', onClick: onActionClick },
        name: 'CellOperation',
        options: [
          'edit',
          { code: 'delete', danger: true, text: $t('common.delete') },
        ],
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

export const useColumns = useDictTypeColumns;
