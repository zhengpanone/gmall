<script lang="ts" setup>
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemDeptApi } from '#/api/system/dept';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { IconifyIcon } from '@vben/icons';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteDept, getDeptList } from '#/api/system/dept';

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
    pagerConfig: { enabled: false },
    proxyConfig: {
      ajax: {
        query: async () => {
          return await getDeptList();
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
    treeConfig: {
      parentField: 'parentId',
      rowField: 'id',
      transform: true,
    },
  } as VxeTableGridOptions,
});

function onActionClick({ code, row }: OnActionClickParams<SystemDeptApi.Dept>) {
  switch (code) {
    case 'append': {
      onAppend(row);
      break;
    }
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

function onEdit(row: SystemDeptApi.Dept) {
  formDrawerApi.setData(row).open();
}

function onCreate() {
  formDrawerApi.setData({} as SystemDeptApi.Dept).open();
}

function onAppend(row: SystemDeptApi.Dept) {
  formDrawerApi.setData({ parentId: row.id } as SystemDeptApi.Dept).open();
}

function onDelete(row: SystemDeptApi.Dept) {
  ElMessage({
    message: $t('ui.actionMessage.deleting', [row.name]),
    duration: 0,
  });
  deleteDept(row.id!).then(() => {
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
          {{ $t('ui.actionTitle.create', [$t('system.dept.deptManage')]) }}
        </ElButton>
      </template>
      <template #name="{ row }">
        <div class="flex w-full items-center gap-1">
          <IconifyIcon icon="carbon:folder" class="size-5 shrink-0" />
          <span class="flex-auto">{{ row.name }}</span>
        </div>
      </template>
    </Grid>
  </Page>
</template>
