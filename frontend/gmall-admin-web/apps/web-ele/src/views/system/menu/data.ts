import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemMenuApi } from '#/api/system/menu';

import { SystemMenuTypeOptions } from '#/constants';
import { $t } from '#/locales';

/**
 * 生成系统菜单表格的列配置
 * @param {OnActionClickFn<SystemMenuApi.Menu>} onActionClick - 操作按钮点击回调函数
 * @returns {VxeTableGridColumns<SystemMenuApi.Menu>} vxe-table 表格列配置数组
 */
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
      align: 'left',
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
      align: 'left',
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
      align: 'center',
      cellRender: {
        attrs: {
          nameField: 'name',
          onClick: onActionClick,
        },
        name: 'CellOperation',
        options: [
          { code: 'append', text: $t('system.menu.appendChild') },
          { code: 'edit', text: $t('common.edit') },
          { code: 'delete', text: $t('common.delete'), type: 'danger', danger: true, renderConfirm: true, confirmType:'messagebox' },
        ],
      },
      field: 'operation',
      fixed: 'right',
      headerAlign: 'center',
      showOverflow: false,
      title: $t('page.common.operation'),
      width: 360,
      minWidth: 320,
    },
  ];
}
