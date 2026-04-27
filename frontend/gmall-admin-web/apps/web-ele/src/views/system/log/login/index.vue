<script lang="ts" setup>
import type { VxeTableGridOptions } from '#/adapter/vxe-table';

import { Page } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { cleanLoginLog, getLoginLogList } from '#/api/system/loginlog';

import { useColumns } from './data';

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: useColumns(),
    height: 'auto',
    keepSource: true,
    pagerConfig: { enabled: true },
    proxyConfig: {
      ajax: {
        query: async () => {
          return await getLoginLogList();
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

function onRefresh() {
  gridApi.query();
}

function onClean() {
  ElMessage({
    message: $t('ui.actionMessage.clearing'),
    duration: 0,
  });
  cleanLoginLog().then(() => {
    ElMessage.success($t('ui.actionMessage.clearSuccess'));
    onRefresh();
  });
}
</script>

<template>
  <Page auto-content-height>
    <Grid>
      <template #toolbar-tools>
        <ElButton danger @click="onClean">
          {{ $t('ui.actionTitle.clean') }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
