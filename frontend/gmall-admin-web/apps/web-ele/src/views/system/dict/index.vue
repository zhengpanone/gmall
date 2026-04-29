<script lang="ts" setup>
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemDictApi } from '#/api/system/dict';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteDict, getDictPageList } from '#/api/system/dict';

import { useColumns } from './data';
import Form from './modules/form.vue';

const [FormDrawer, formDrawerApi] = useVbenDrawer({
  connectedComponent: Form,
  destroyOnClose: true,
});

const [Grid, gridApi] = useVbenVxeGrid({
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
          const params: SystemDictApi.DictPageParam = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };
          // formOptions 模式下筛选值来自 query 的第2个参数；这里兼容两种来源
          const queryForm = {
            ...form,
            ...formValues,
          } as Record<string, any>;
          return await getDictPageList({ ...params, ...queryForm });
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

function onActionClick({ code, row }: OnActionClickParams<SystemDictApi.Dict>) {
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

function onEdit(row: SystemDictApi.Dict) {
  formDrawerApi.setData(row).open();
}

function onCreate() {
  formDrawerApi.setData({} as SystemDictApi.Dict).open();
}

function onDelete(row: SystemDictApi.Dict) {
  ElMessage({
    message: $t('ui.actionMessage.deleting', [row.name]),
    duration: 0,
  });
  deleteDict([row.id!]).then(() => {
    ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.name]));
    onRefresh();
  });
}
</script>

<template>
  <Page auto-content-height>
    <FormDrawer @success="onRefresh" />
    <Grid>
      <template #toolbar-tools>
        <ElButton type="primary" @click="onCreate">
          {{ $t('ui.actionTitle.create', [$t('system.dict.dictManage')]) }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
