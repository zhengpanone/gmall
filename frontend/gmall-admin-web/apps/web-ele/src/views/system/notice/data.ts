import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemNoticeApi } from '#/api/system/notice';

import { $t } from '#/locales';

export function useColumns(
  onActionClick: OnActionClickFn<SystemNoticeApi.Notice>,
): VxeTableGridColumns<SystemNoticeApi.Notice> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'title',
      title: $t('system.notice.title'),
      width: 200,
    },
    {
      cellRender: { name: 'CellTag', options: [
        { label: $t('system.notice.type1'), value: 1, color: 'primary' },
        { label: $t('system.notice.type2'), value: 2, color: 'success' },
      ]},
      field: 'type',
      title: $t('system.notice.type'),
      width: 120,
    },
    {
      field: 'creator',
      title: $t('system.notice.creator'),
      width: 120,
    },
    {
      field: 'createTime',
      title: $t('system.notice.createTime'),
      width: 180,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.notice.status'),
      width: 100,
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
