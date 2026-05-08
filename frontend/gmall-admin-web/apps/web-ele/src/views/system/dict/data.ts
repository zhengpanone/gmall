import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemDictApi } from '#/api/system/dict';

import { $t } from '#/locales';

/**
 * 生成字典类型表格的列配置
 * @param onActionClick - 操作按钮点击回调函数
 * @returns VxeTable 表格列配置数组
 */
export function useDictTypeColumns(
  onActionClick: OnActionClickFn<SystemDictApi.DictType>,
): VxeTableGridColumns<SystemDictApi.DictType> {
  return [
    { type: 'checkbox', width: 46 },
    {
      field: 'typeName',
      title: $t('system.dict.typeName'),
      minWidth: 120,
    },
    {
      field: 'typeCode',
      title: $t('system.dict.typeCode'),
      minWidth: 120,
    },
    {
      field: 'type',
      title: $t('system.dict.type'),
      minWidth: 150,
      formatter: ({ row }) =>
        Number(row.type) === 2 ? $t('system.dict.businessType') : $t('system.dict.systemType'),
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
      formatter: ({ row }) => String(row.createTime ?? ''),
    },
    {
      align: 'right',
      cellRender: {
        attrs: { onClick: onActionClick },
        name: 'CellOperation',
        options: ['edit', { code: 'delete', danger: true, text: $t('common.delete') }],
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

export function useDictDataColumns(
  onActionClick: OnActionClickFn<SystemDictApi.DictData>,
): VxeTableGridColumns<SystemDictApi.DictData> {
  return [
    { type: 'checkbox', width: 46 },
    {
      field: 'dataName',
      minWidth: 120,
      slots: { default: 'dictDataLabel' },
      treeNode: true,
      title: $t('system.dict.dataLabel'),
    },
    {
      field: 'dataCode',
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
          { code: 'append', text: '新增下级' },
          'edit',
          { code: 'delete', danger: true, text: $t('common.delete') },
        ],
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

export const useColumns = useDictTypeColumns;
