<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemOperLogApi } from '#/api/system/operlog';

import { computed, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage, ElMessageBox } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  cleanOperLog,
  deleteOperLog,
  getOperLogPageList,
} from '#/api/system/operlog';

import {
  businessTypeOptions,
  formatBusinessType,
  formatOperStatus,
  getOperTitle,
  getOperUrl,
  operStatusOptions,
  useColumns,
} from './data';
import {
  formatDateTimeValue,
  getRowText,
  normalizePageResult,
} from '../utils';

const currentRow = ref<SystemOperLogApi.OperLog>();

interface DetailItem {
  label: string;
  value: string;
  wide?: boolean;
}

const operQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'title',
    label: $t('system.log.title'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.log.title'),
    },
  },
  {
    component: 'Input',
    fieldName: 'operName',
    label: $t('system.log.operName'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.log.operName'),
    },
  },
  {
    component: 'Select',
    fieldName: 'businessType',
    label: $t('system.log.businessType'),
    componentProps: {
      clearable: true,
      options: businessTypeOptions,
      placeholder: $t('system.log.businessType'),
    },
  },
  {
    component: 'Select',
    fieldName: 'status',
    label: $t('system.log.status'),
    componentProps: {
      clearable: true,
      options: operStatusOptions,
      placeholder: $t('system.log.status'),
    },
  },
  {
    component: 'DatePicker',
    fieldName: 'timeRange',
    label: $t('system.log.operTime'),
    componentProps: {
      clearable: true,
      endPlaceholder: $t('system.log.endTime'),
      startPlaceholder: $t('system.log.startTime'),
      type: 'daterange',
      valueFormat: 'YYYY-MM-DD',
    },
  },
];

const [DetailDrawer, detailDrawerApi] = useVbenDrawer({
  footer: false,
  onOpenChange(isOpen) {
    currentRow.value = isOpen
      ? detailDrawerApi.getData<SystemOperLogApi.OperLog>()
      : undefined;
  },
});

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    fieldMappingTime: [
      [
        'timeRange',
        ['startTime', 'endTime'],
        ['YYYY-MM-DD 00:00:00', 'YYYY-MM-DD 23:59:59'],
      ],
    ],
    schema: operQuerySchema,
    showCollapseButton: true,
  },
  gridOptions: {
    columns: useColumns(onActionClick),
    height: 'auto',
    keepSource: true,
    pagerConfig: { enabled: true },
    proxyConfig: {
      ajax: {
        query: async ({ form, page }, formValues = {}) => {
          const currentPage = page?.currentPage ?? 1;
          const currentPageSize = page?.pageSize ?? 20;
          const queryForm = {
            ...form,
            ...formValues,
          } as Record<string, any>;
          const params: SystemOperLogApi.OperLogQueryParams = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };

          if (queryForm.title?.trim()) params.title = queryForm.title.trim();
          if (queryForm.operName?.trim()) {
            params.operName = queryForm.operName.trim();
          }
          if (queryForm.businessType !== undefined && queryForm.businessType !== null) {
            params.businessType = queryForm.businessType;
          }
          if (queryForm.status !== undefined && queryForm.status !== null) {
            params.status = queryForm.status;
          }
          if (queryForm.startTime) params.startTime = queryForm.startTime;
          if (queryForm.endTime) params.endTime = queryForm.endTime;

          const result = normalizePageResult<SystemOperLogApi.OperLog>(
            await getOperLogPageList(params),
            currentPage,
            currentPageSize,
          );
          result.list = result.list.map(normalizeOperLogRow);
          return result;
        },
      },
    },
    rowConfig: { keyField: 'id' },
    toolbarConfig: {
      custom: true,
      export: true,
      refresh: true,
      zoom: true,
    },
  } as VxeTableGridOptions,
});

const detailItems = computed<DetailItem[]>(() => {
  const row = currentRow.value;
  return [
    {
      label: $t('system.log.title'),
      value: getOperTitle(row),
    },
    {
      label: $t('system.log.businessType'),
      value: formatBusinessType(row?.businessType ?? row?.businessTypeName),
    },
    {
      label: $t('system.log.status'),
      value: formatOperStatus(row?.status),
    },
    {
      label: $t('system.log.operName'),
      value: getRowText(row, ['operName', 'userName']),
    },
    {
      label: $t('system.log.operIp'),
      value: getRowText(row, ['operIp', 'userIp']),
    },
    {
      label: $t('system.log.operLocation'),
      value: getRowText(row, ['operLocation']),
    },
    {
      label: $t('system.log.requestMethod'),
      value: getRowText(row, ['requestMethod']),
    },
    {
      label: $t('system.log.method'),
      value: getRowText(row, ['method']),
    },
    {
      label: $t('system.log.operUrl'),
      value: getOperUrl(row),
      wide: true,
    },
    {
      label: $t('system.log.costTime'),
      value:
        row?.costTime === undefined || row.costTime === null || row.costTime === ''
          ? '-'
          : `${row.costTime} ms`,
    },
    {
      label: $t('system.log.operTime'),
      value: formatDateTimeValue(row?.operTime ?? row?.createTime),
    },
    {
      label: $t('system.log.operParam'),
      value: getRowText(row, ['operParam', 'extra']),
      wide: true,
    },
    {
      label: $t('system.log.jsonResult'),
      value: getRowText(row, ['jsonResult', 'action']),
      wide: true,
    },
    {
      label: $t('system.log.errorMsg'),
      value: getRowText(row, ['errorMsg']),
      wide: true,
    },
  ];
});

function onActionClick({
  code,
  row,
}: OnActionClickParams<SystemOperLogApi.OperLog>) {
  switch (code) {
    case 'detail': {
      detailDrawerApi.setData(row).open();
      break;
    }
    case 'remove': {
      onDelete(row);
      break;
    }
    default: {
      break;
    }
  }
}

function onRefresh() {
  gridApi.query();
}

function getSelectedRows() {
  const selectedRows = (gridApi.grid as any)?.getCheckboxRecords?.();
  return Array.isArray(selectedRows) ? selectedRows : [];
}

function normalizeOperLogRow(row: SystemOperLogApi.OperLog) {
  return {
    ...row,
    businessType: row.businessType ?? row.businessTypeName ?? row.subType,
    method: row.method ?? row.requestMethod,
    operIp: row.operIp ?? row.userIp,
    operName: row.operName ?? row.userName,
    operUrl: row.operUrl ?? row.requestUrl,
    title: row.title ?? row.type,
  };
}

async function onDelete(row: SystemOperLogApi.OperLog) {
  if (!row.id) return;

  try {
    await ElMessageBox.confirm(
      $t('ui.actionMessage.deleteConfirm', [getOperTitle(row)]),
      $t('ui.actionTitle.delete', [$t('system.log.operLog')]),
      {
        cancelButtonText: $t('common.cancel'),
        confirmButtonClass: 'el-button--danger',
        confirmButtonText: $t('common.confirm'),
        type: 'warning',
      },
    );
  } catch {
    return;
  }

  const loadingMsg = ElMessage({
    duration: 0,
    message: $t('ui.actionMessage.deleting', [getOperTitle(row)]),
  });

  deleteOperLog([row.id])
    .then(() => {
      ElMessage.success(
        $t('ui.actionMessage.deleteSuccess', [getOperTitle(row)]),
      );
      onRefresh();
    })
    .catch(() => {
      // 接口错误信息已被全局拦截器统一处理，此处无需额外操作
    })
    .finally(() => {
      loadingMsg.close();
    });
}

async function onBatchDelete() {
  const selectedRows = getSelectedRows();
  const ids = selectedRows
    .map((item) => item?.id)
    .filter((id): id is number | string => id !== undefined && id !== null);

  if (ids.length === 0) {
    ElMessage.warning($t('system.log.selectToDelete'));
    return;
  }

  try {
    await ElMessageBox.confirm(
      $t('ui.actionMessage.deleteConfirm', [
        `${ids.length} ${$t('system.log.operLog')}`,
      ]),
      $t('ui.actionTitle.delete', ['']),
      {
        cancelButtonText: $t('common.cancel'),
        confirmButtonClass: 'el-button--danger',
        confirmButtonText: $t('common.confirm'),
        type: 'warning',
      },
    );
  } catch {
    return;
  }

  const loadingMsg = ElMessage({
    duration: 0,
    message: $t('ui.actionMessage.deleting', [
      `${ids.length} ${$t('system.log.operLog')}`,
    ]),
  });

  deleteOperLog(ids)
    .then(() => {
      ElMessage.success($t('system.log.batchDeleteSuccess', [ids.length]));
      (gridApi.grid as any)?.clearCheckboxRow?.();
      onRefresh();
    })
    .catch(() => {
      // 接口错误信息已被全局拦截器统一处理，此处无需额外操作
    })
    .finally(() => {
      loadingMsg.close();
    });
}

async function onClean() {
  try {
    await ElMessageBox.confirm(
      $t('system.log.cleanConfirm', [$t('system.log.operLog')]),
      $t('ui.actionTitle.clean'),
      {
        cancelButtonText: $t('common.cancel'),
        confirmButtonClass: 'el-button--danger',
        confirmButtonText: $t('common.confirm'),
        type: 'warning',
      },
    );
  } catch {
    return;
  }

  const loadingMsg = ElMessage({
    duration: 0,
    message: $t('ui.actionMessage.clearing'),
  });

  cleanOperLog()
    .then(() => {
      ElMessage.success($t('ui.actionMessage.clearSuccess'));
      onRefresh();
    })
    .catch(() => {
      // 接口错误信息已被全局拦截器统一处理，此处无需额外操作
    })
    .finally(() => {
      loadingMsg.close();
    });
}
</script>

<template>
  <Page auto-content-height>
    <DetailDrawer
      class="w-full max-w-[880px]"
      :title="$t('ui.actionTitle.view', [$t('system.log.operLog')])"
    >
      <div class="grid grid-cols-1 gap-3 px-4 pb-4 md:grid-cols-2">
        <div
          v-for="item in detailItems"
          :key="item.label"
          class="rounded border border-border px-3 py-2"
          :class="{ 'md:col-span-2': item.wide }"
        >
          <div class="text-muted-foreground text-xs">{{ item.label }}</div>
          <pre class="mt-1 whitespace-pre-wrap break-all font-sans text-sm leading-6">{{ item.value }}</pre>
        </div>
      </div>
    </DetailDrawer>
    <Grid>
      <template #toolbar-tools>
        <ElButton type="danger" @click="onBatchDelete">
          {{ $t('system.log.batchDelete') }}
        </ElButton>
        <ElButton danger @click="onClean">
          {{ $t('ui.actionTitle.clean') }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
