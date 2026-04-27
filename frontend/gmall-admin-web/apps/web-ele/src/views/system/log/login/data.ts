import type { VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemLoginLogApi } from '#/api/system/loginlog';

import { $t } from '#/locales';

export function useColumns(): VxeTableGridColumns<SystemLoginLogApi.LoginLog> {
  return [
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'userName',
      title: $t('system.loginlog.userName'),
      width: 120,
    },
    {
      field: 'ipaddr',
      title: $t('system.loginlog.ipaddr'),
      width: 140,
    },
    {
      field: 'loginLocation',
      title: $t('system.loginlog.loginLocation'),
      width: 200,
    },
    {
      field: 'browser',
      title: $t('system.loginlog.browser'),
      width: 150,
    },
    {
      field: 'os',
      title: $t('system.loginlog.os'),
      width: 150,
    },
    {
      cellRender: { name: 'CellTag' },
      field: 'status',
      title: $t('system.loginlog.status'),
      width: 100,
    },
    {
      field: 'msg',
      title: $t('system.loginlog.msg'),
      width: 150,
    },
    {
      field: 'loginTime',
      title: $t('system.loginlog.loginTime'),
      width: 180,
    },
  ];
}
