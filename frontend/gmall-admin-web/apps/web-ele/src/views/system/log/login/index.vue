<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemLoginLogApi } from '#/api/system/loginlog';

import { computed, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage, ElMessageBox } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  cleanLoginLog,
  deleteLoginLog,
  getLoginLogPageList,
} from '#/api/system/loginlog';

import {
  formatLoginResult,
  getLoginIp,
  getLoginResult,
  getLoginUsername,
  loginResultOptions,
  useColumns,
} from './data';
import {
  formatDateTimeValue,
  getRowText,
  normalizePageResult,
} from '../utils';

const currentRow = ref<SystemLoginLogApi.LoginLog>();

interface DetailItem {
  label: string;
  value: string;
  wide?: boolean;
}

const loginQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'username',
    label: $t('system.log.userName'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.log.userName'),
    },
  },
  {
    component: 'Input',
    fieldName: 'userIp',
    label: $t('system.log.ipaddr'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.log.ipaddr'),
    },
  },
  {
    component: 'Select',
    fieldName: 'result',
    label: $t('system.log.result'),
    componentProps: {
      clearable: true,
      options: loginResultOptions,
      placeholder: $t('system.log.result'),
    },
  },
  {
    component: 'DatePicker',
    fieldName: 'timeRange',
    label: $t('system.log.loginTime'),
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
      ? detailDrawerApi.getData<SystemLoginLogApi.LoginLog>()
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
    schema: loginQuerySchema,
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
          const params: SystemLoginLogApi.LoginLogQueryParams = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };

          if (queryForm.username?.trim()) {
            params.username = queryForm.username.trim();
            params.userName = queryForm.username.trim();
          }
          if (queryForm.userIp?.trim()) {
            params.userIp = queryForm.userIp.trim();
            params.ipaddr = queryForm.userIp.trim();
          }
          if (queryForm.result !== undefined && queryForm.result !== null) {
            params.result = queryForm.result;
            params.status = queryForm.result;
          }
          if (queryForm.startTime) params.startTime = queryForm.startTime;
          if (queryForm.endTime) params.endTime = queryForm.endTime;

          const result = normalizePageResult<SystemLoginLogApi.LoginLog>(
            await getLoginLogPageList(params),
            currentPage,
            currentPageSize,
          );
          result.list = result.list.map(normalizeLoginLogRow);
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
      label: $t('system.log.userName'),
      value: getLoginUsername(row),
    },
    {
      label: $t('system.log.ipaddr'),
      value: getLoginIp(row),
    },
    {
      label: $t('system.log.result'),
      value: formatLoginResult(getLoginResult(row)),
    },
    {
      label: $t('system.log.loginLocation'),
      value: getRowText(row, ['loginLocation']),
    },
    {
      label: $t('system.log.browser'),
      value: getRowText(row, ['browser']),
    },
    {
      label: $t('system.log.os'),
      value: getRowText(row, ['os']),
    },
    {
      label: $t('system.log.msg'),
      value: getRowText(row, ['msg']),
      wide: true,
    },
    {
      label: $t('system.log.userAgent'),
      value: getRowText(row, ['userAgent']),
      wide: true,
    },
    {
      label: $t('system.log.loginTime'),
      value: formatDateTimeValue(row?.loginTime ?? row?.createTime),
    },
  ];
});

function onActionClick({
  code,
  row,
}: OnActionClickParams<SystemLoginLogApi.LoginLog>) {
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

function normalizeLoginLogRow(row: SystemLoginLogApi.LoginLog) {
  return {
    ...row,
    ipaddr: row.ipaddr ?? row.userIp,
    result: row.result ?? row.status,
    username: row.username ?? row.userName,
  };
}

async function onDelete(row: SystemLoginLogApi.LoginLog) {
  if (!row.id) return;

  try {
    await ElMessageBox.confirm(
      $t('ui.actionMessage.deleteConfirm', [getLoginUsername(row)]),
      $t('ui.actionTitle.delete', [$t('system.log.loginLog')]),
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
    message: $t('ui.actionMessage.deleting', [getLoginUsername(row)]),
  });

  deleteLoginLog([row.id])
    .then(() => {
      ElMessage.success(
        $t('ui.actionMessage.deleteSuccess', [getLoginUsername(row)]),
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
        `${ids.length} ${$t('system.log.loginLog')}`,
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
      `${ids.length} ${$t('system.log.loginLog')}`,
    ]),
  });

  deleteLoginLog(ids)
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
      $t('system.log.cleanConfirm', [$t('system.log.loginLog')]),
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

  cleanLoginLog()
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
      :title="$t('ui.actionTitle.view', [$t('system.log.loginLog')])"
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
