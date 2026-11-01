import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemLoginLogApi } from '#/api/system/loginlog';

import { $t } from '#/locales';

import { formatDateTimeValue, getRowText } from '../utils';

type OptionValue = number | string;

interface LogOption {
  color?: string;
  label: string;
  value: OptionValue;
}

export const loginResultOptions: LogOption[] = [
  { label: $t('system.log.success'), value: 0 },
  { label: $t('system.log.fail'), value: 1 },
  { label: $t('system.log.badCredentials'), value: 10 },
  { label: $t('system.log.userDisabled'), value: 20 },
  { label: $t('system.log.captchaNotFound'), value: 30 },
  { label: $t('system.log.captchaError'), value: 31 },
];

export const loginResultTagOptions = withStringValues([
  { color: 'success', label: $t('system.log.success'), value: 0 },
  { color: 'danger', label: $t('system.log.fail'), value: 1 },
  { color: 'danger', label: $t('system.log.badCredentials'), value: 10 },
  { color: 'danger', label: $t('system.log.userDisabled'), value: 20 },
  { color: 'danger', label: $t('system.log.captchaNotFound'), value: 30 },
  { color: 'danger', label: $t('system.log.captchaError'), value: 31 },
]);

export function formatLoginResult(value: any) {
  const item = loginResultTagOptions.find(
    (option) => String(option.value) === String(value),
  );
  return item?.label ?? (value === undefined || value === null || value === '' ? '-' : String(value));
}

export function getLoginUsername(row?: SystemLoginLogApi.LoginLog | null) {
  return getRowText(row, ['username', 'userName']);
}

export function getLoginIp(row?: SystemLoginLogApi.LoginLog | null) {
  return getRowText(row, ['userIp', 'ipaddr']);
}

export function getLoginResult(row?: SystemLoginLogApi.LoginLog | null) {
  if (!row) return undefined;
  return row.result ?? row.status;
}

export function useColumns(
  onActionClick: OnActionClickFn<SystemLoginLogApi.LoginLog>,
): VxeTableGridColumns<SystemLoginLogApi.LoginLog> {
  return [
    { type: 'checkbox', width: 56, fixed: 'left' },
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'username',
      formatter: ({ row }) => getLoginUsername(row),
      title: $t('system.log.userName'),
      width: 120,
    },
    {
      field: 'ipaddr',
      formatter: ({ row }) => getLoginIp(row),
      title: $t('system.log.ipaddr'),
      width: 140,
    },
    {
      field: 'loginLocation',
      title: $t('system.log.loginLocation'),
      width: 200,
    },
    {
      field: 'browser',
      title: $t('system.log.browser'),
      width: 150,
    },
    {
      field: 'os',
      title: $t('system.log.os'),
      width: 150,
    },
    {
      cellRender: {
        name: 'CellTag',
        options: loginResultTagOptions,
      },
      field: 'result',
      formatter: ({ row }) => formatLoginResult(getLoginResult(row)),
      title: $t('system.log.result'),
      width: 100,
    },
    {
      field: 'msg',
      title: $t('system.log.msg'),
      width: 180,
    },
    {
      field: 'userAgent',
      formatter: ({ row }) => getRowText(row, ['userAgent']),
      minWidth: 260,
      title: $t('system.log.userAgent'),
    },
    {
      field: 'loginTime',
      formatter: ({ row }) =>
        formatDateTimeValue(row.loginTime ?? row.createTime),
      title: $t('system.log.loginTime'),
      width: 180,
    },
    {
      align: 'right',
      cellRender: {
        attrs: { onClick: onActionClick },
        name: 'CellOperation',
        options: [
          { code: 'detail', text: $t('ui.actionTitle.view', ['']) },
          { code: 'remove', danger: true, text: $t('common.delete') },
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

function withStringValues(options: LogOption[]) {
  return options.flatMap((option) => [
    option,
    { ...option, value: String(option.value) },
  ]);
}
