import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemDeptApi } from '#/api/system/dept';

import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemDeptApi.Dept>,
): VxeTableGridColumns<SystemDeptApi.Dept> {
  return [
    {
      field: 'name',
      fixed: 'left',
      slots: { default: 'name' },
      title: $t('system.dept.name'),
      treeNode: true,
      width: 250,
    },
    {
      field: 'sort',
      title: $t('system.dept.sort'),
      width: 100,
    },
    {
      field: 'leader',
      title: $t('system.dept.leader'),
      width: 120,
    },
    {
      field: 'phone',
      title: $t('system.dept.phone'),
      width: 130,
    },
    {
      field: 'email',
      title: $t('system.dept.email'),
      width: 180,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.dept.status'),
      width: 100,
    },
    {
      align: 'right',
      cellRender: {
        attrs: { onClick: onActionClick },
        name: 'CellOperation',
        options: [
          { code: 'append', text: $t('system.dept.appendChild') },
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
