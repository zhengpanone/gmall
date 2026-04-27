import type { VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemOperLogApi } from '#/api/system/operlog';

import { $t } from '#/locales';

export function useColumns(): VxeTableGridColumns<SystemOperLogApi.OperLog> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'title',
      title: $t('system.operlog.title'),
      width: 150,
    },
    {
      field: 'businessType',
      title: $t('system.operlog.businessType'),
      width: 100,
    },
    {
      field: 'method',
      title: $t('system.operlog.method'),
      width: 200,
    },
    {
      field: 'requestMethod',
      title: $t('system.operlog.requestMethod'),
      width: 100,
    },
    {
      field: 'operName',
      title: $t('system.operlog.operName'),
      width: 120,
    },
    {
      field: 'operIp',
      title: $t('system.operlog.operIp'),
      width: 140,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.operlog.status'),
      width: 100,
    },
    {
      field: 'costTime',
      title: $t('system.operlog.costTime'),
      width: 100,
    },
    {
      field: 'operTime',
      title: $t('system.operlog.operTime'),
      width: 180,
    },
  ];
}
