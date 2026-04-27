import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemMenuApi } from '#/api/system/menu';

import { SystemMenuTypeOptions } from '#/constants';
import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemMenuApi.Menu>,
): VxeTableGridColumns<SystemMenuApi.Menu> {
  return [
    {
      align: 'left',
      field: 'name',
      fixed: 'left',
      slots: { default: 'name' },
      title: $t('system.menu.menuName'),
      treeNode: true,
      width: 250,
    },
    {
      align: 'center',
      field: 'parentId',
      slots: { default: 'parentId' },
      title: $t('system.menu.parent'),
      width: 150,
    },
    {
      align: 'center',
      cellRender: { name: 'CellTag', options: SystemMenuTypeOptions },
      field: 'type',
      title: $t('system.menu.type'),
      width: 100,
    },
    {
      field: 'path',
      title: $t('system.menu.path'),
      width: 180,
    },
    {
      field: 'permission',
      title: $t('system.menu.permission'),
      width: 150,
    },
    {
      field: 'component',
      title: $t('system.menu.component'),
      minWidth: 180,
    },
    {
      field: 'sort',
      title: $t('system.menu.sort'),
      width: 80,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.menu.status'),
      width: 100,
    },
    {
      align: 'right',
      cellRender: {
        attrs: {
          nameField: 'name',
          onClick: onActionClick,
        },
        name: 'CellOperation',
        options: [
          { code: 'append', text: $t('system.menu.appendChild') },
          'edit',
          'delete',
        ],
      },
      field: 'operation',
      fixed: 'right',
      headerAlign: 'center',
      showOverflow: false,
      title: $t('page.common.operation'),
      width: 200,
    },
  ];
}
