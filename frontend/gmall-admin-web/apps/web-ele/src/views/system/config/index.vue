<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { OnActionClickParams, VxeTableGridOptions } from '#/adapter/vxe-table';
import type { SystemConfigApi } from '#/api/system/config';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage, ElMessageBox } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { CommonStatusEnum } from '#/api/core/common';
import { deleteConfig, getConfigPageList } from '#/api/system/config';

import { useColumns } from './data';
import Form from './modules/form.vue';

const [FormDrawer, formDrawerApi] = useVbenDrawer({
  connectedComponent: Form,
  destroyOnClose: true,
});

const configQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'configName',
    label: $t('system.config.name'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.config.name'),
    },
  },
  {
    component: 'Input',
    fieldName: 'configKey',
    label: $t('system.config.key'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.config.key'),
    },
  },
  {
    component: 'Input',
    fieldName: 'configType',
    label: $t('system.config.type'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.config.type'),
    },
  },
  {
    component: 'Select',
    fieldName: 'status',
    label: $t('system.config.status'),
    componentProps: {
      clearable: true,
      options: [
        { label: $t('common.enabled'), value: CommonStatusEnum.ENABLED },
        { label: $t('common.disabled'), value: CommonStatusEnum.DISABLED },
      ],
      placeholder: $t('system.config.status'),
    },
  },
];

function normalizeConfigPageResult(
  response: any,
  currentPage: number,
  currentPageSize: number,
) {
  const data = response ?? {};
  const list = Array.isArray(data)
    ? data
    : (data.list ??
      data.records ??
      data.rows ??
      data.items ??
      data?.data?.list ??
      data?.data?.records ??
      []);
  const total = Number(
    data.total ?? data.totalCount ?? data?.data?.total ?? list.length ?? 0,
  );
  const pageSize = Number(data.pageSize ?? data.size ?? currentPageSize);
  const pageNum = Number(data.pageNum ?? data.pageNo ?? data.current ?? currentPage);

  return {
    ...data,
    list,
    pageNum,
    pageSize,
    pages: Number(data.pages ?? Math.ceil(total / (pageSize || 1))),
    total,
  };
}

function getTrimmedValue(value: unknown) {
  return typeof value === 'string' ? value.trim() : value;
}

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: configQuerySchema,
    showCollapseButton: false,
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
          const params: SystemConfigApi.SysConfigPageParam = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };

          const configName = getTrimmedValue(queryForm.configName);
          const configKey = getTrimmedValue(queryForm.configKey);
          const configType = getTrimmedValue(queryForm.configType);

          if (configName) params.configName = configName as string;
          if (configKey) params.configKey = configKey as string;
          if (configType) params.configType = configType as string;
          if (queryForm.status !== undefined && queryForm.status !== null) {
            params.status = queryForm.status;
          }

          const result = await getConfigPageList(params).catch(() => {
            ElMessage.error($t('common.actionMessage.queryFailed'));
            return {
              list: [],
              total: 0,
            };
          });

          return normalizeConfigPageResult(result, currentPage, currentPageSize);
        },
      },
    },
    rowConfig: { keyField: 'id' },
    toolbarConfig: {
      custom: true,
      export: false,
      refresh: true,
      zoom: true,
    },
  } as VxeTableGridOptions,
});

function onActionClick({ code, row }: OnActionClickParams<SystemConfigApi.Config>) {
  switch (code) {
    case 'delete': {
      onDelete(row);
      break;
    }
    case 'edit': {
      onEdit(row);
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

function onEdit(row: SystemConfigApi.Config) {
  formDrawerApi.setData(row).open();
}

function onCreate() {
  formDrawerApi.setData({} as SystemConfigApi.Config).open();
}

function getConfigTitle(row: SystemConfigApi.Config) {
  return row.configName || row.configKey || '';
}

function onDelete(row: SystemConfigApi.Config) {
  if (!row.id) return;

  const title = getConfigTitle(row);
  ElMessageBox.confirm(
    $t('ui.actionMessage.deleteConfirm', [title]),
    $t('ui.actionTitle.delete', [$t('system.config.configManage')]),
    {
      type: 'warning',
    },
  )
    .then(() => deleteConfig([row.id as string]))
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [title]));
      onRefresh();
    })
    .catch(() => {
      // User cancellation and API errors are handled elsewhere.
    });
}
</script>

<template>
  <Page auto-content-height>
    <FormDrawer @success="onRefresh" />
    <Grid>
      <template #toolbar-tools>
        <ElButton type="primary" @click="onCreate">
          {{ $t('ui.actionTitle.create', [$t('system.config.configManage')]) }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
