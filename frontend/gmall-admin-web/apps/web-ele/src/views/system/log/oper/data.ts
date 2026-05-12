import type { OnActionClickFn, VxeTableGridColumns } from '#/adapter/vxe-table';
import type { SystemOperLogApi } from '#/api/system/operlog';

import { $t } from '#/locales';

import { formatDateTimeValue, getRowText } from '../utils';

type OptionValue = number | string;

interface LogOption {
  color?: string;
  label: string;
  value: OptionValue;
}

export const businessTypeOptions: LogOption[] = [
  { label: $t('system.log.businessTypeOther'), value: 0 },
  { label: $t('system.log.businessTypeAdd'), value: 1 },
  { label: $t('system.log.businessTypeUpdate'), value: 2 },
  { label: $t('system.log.businessTypeDelete'), value: 3 },
  { label: $t('system.log.businessTypeGrant'), value: 4 },
  { label: $t('system.log.businessTypeExport'), value: 5 },
  { label: $t('system.log.businessTypeImport'), value: 6 },
  { label: $t('system.log.businessTypeForce'), value: 7 },
  { label: $t('system.log.businessTypeGenerate'), value: 8 },
  { label: $t('system.log.businessTypeClean'), value: 9 },
];

export const operStatusOptions: LogOption[] = [
  { label: $t('system.log.success'), value: 1 },
  { label: $t('system.log.fail'), value: 0 },
];

const businessTypeTagOptions = withStringValues(businessTypeOptions);
const operStatusTagOptions = withStringValues([
  { color: 'success', label: $t('system.log.success'), value: 1 },
  { color: 'danger', label: $t('system.log.fail'), value: 0 },
]);

export function formatBusinessType(value: any) {
  return getOptionLabel(businessTypeTagOptions, value);
}

export function formatOperStatus(value: any) {
  return getOptionLabel(operStatusTagOptions, value);
}

export function getOperTitle(row?: SystemOperLogApi.OperLog | null) {
  return getRowText(row, ['title', 'type']);
}

export function getOperUrl(row?: SystemOperLogApi.OperLog | null) {
  return getRowText(row, ['operUrl', 'requestUrl']);
}

export function useColumns(
  onActionClick: OnActionClickFn<SystemOperLogApi.OperLog>,
): VxeTableGridColumns<SystemOperLogApi.OperLog> {
  return [
    { type: 'checkbox', width: 56, fixed: 'left' },
    { type: 'seq', width: 60, title: '#' },
    {
      field: 'title',
      formatter: ({ row }) => getOperTitle(row),
      title: $t('system.log.title'),
      width: 150,
    },
    {
      cellRender: {
        name: 'CellTag',
        options: businessTypeTagOptions,
      },
      field: 'businessType',
      formatter: ({ row }) => getRowText(row, ['businessTypeName', 'subType']),
      title: $t('system.log.businessType'),
      width: 100,
    },
    {
      field: 'method',
      formatter: ({ row }) => getRowText(row, ['method', 'requestMethod']),
      title: $t('system.log.method'),
      width: 220,
    },
    {
      field: 'requestMethod',
      title: $t('system.log.requestMethod'),
      width: 100,
    },
    {
      field: 'operUrl',
      formatter: ({ row }) => getOperUrl(row),
      minWidth: 220,
      title: $t('system.log.operUrl'),
    },
    {
      field: 'operName',
      title: $t('system.log.operName'),
      width: 120,
    },
    {
      field: 'operIp',
      formatter: ({ row }) => getRowText(row, ['operIp', 'userIp']),
      title: $t('system.log.operIp'),
      width: 140,
    },
    {
      cellRender: {
        name: 'CellTag',
        options: operStatusTagOptions,
      },
      field: 'status',
      title: $t('system.log.status'),
      width: 100,
    },
    {
      field: 'costTime',
      formatter: ({ row }) => {
        const value = row.costTime;
        return value === undefined || value === null || value === ''
          ? '-'
          : `${value} ms`;
      },
      title: $t('system.log.costTime'),
      width: 100,
    },
    {
      field: 'operTime',
      formatter: ({ row }) =>
        formatDateTimeValue(row.operTime ?? row.createTime),
      title: $t('system.log.operTime'),
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

function getOptionLabel(options: LogOption[], value: any) {
  const item = options.find((option) => String(option.value) === String(value));
  return item?.label ?? (value === undefined || value === null || value === '' ? '-' : String(value));
}

function withStringValues(options: LogOption[]) {
  return options.flatMap((option) => [
    option,
    { ...option, value: String(option.value) },
  ]);
}
