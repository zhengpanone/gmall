<script lang="ts" setup>
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemRoleApi } from '#/api/system/role';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteRole, getRoleList } from '#/api/system/role';

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
        query: async () => {
          return await getRoleList();
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

function onActionClick({ code, row }: OnActionClickParams<SystemRoleApi.Role>) {
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

function onEdit(row: SystemRoleApi.Role) {
  formDrawerApi.setData(row).open();
}

function onCreate() {
  formDrawerApi.setData({} as SystemRoleApi.Role).open();
}

function onDelete(row: SystemRoleApi.Role) {
  ElMessage({
    message: $t('ui.actionMessage.deleting', [row.name]),
    duration: 0,
  });
  deleteRole(row.id!).then(() => {
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
          {{ $t('ui.actionTitle.create', [$t('system.role.roleManage')]) }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
