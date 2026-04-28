<script lang="ts" setup>
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemMenuApi } from '#/api/system/menu';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { IconifyIcon } from '@vben/icons';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteMenu, getMenuList } from '#/api/system/menu';
import { SystemMenuTypeEnum } from '#/constants';

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
    pagerConfig: {
      enabled: false,
    },
    proxyConfig: {
      ajax: {
        query: async () => {
          return await getMenuList();
        },
      },
    },
    rowConfig: {
      keyField: 'id',
    },
    toolbarConfig: {
      custom: true,
      export: false,
      refresh: true,
      zoom: true,
    },
    treeConfig: {
      children: 'children',
      expandAll: true,
    },
  } as VxeTableGridOptions,
});

function onActionClick({
  code,
  row,
}: OnActionClickParams<SystemMenuApi.Menu>) {
  switch (code) {
    case 'append': {
      onAppend(row);
      break;
    }
    case 'delete': {
      onDelete(row/* ,'popconfirm' */); // 'popconfirm' | 'messagebox'
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

function onEdit(row: SystemMenuApi.Menu) {
  formDrawerApi.setData(row).open();
}

function onCreate() {
  formDrawerApi.setData({} as SystemMenuApi.Menu).open();
}

function onAppend(row: SystemMenuApi.Menu) {
  formDrawerApi
    .setData({ parentId: row.id } as unknown as SystemMenuApi.Menu)
    .open();
}

async function onDelete(row: SystemMenuApi.Menu/* , confirmType: 'messagebox' | 'popconfirm' = 'popconfirm' */) {
  // popconfirm 模式：renderConfirm: true，气泡已经确认过了，直接删除
  // messagebox 模式：renderConfirm: false，这里弹窗确认
  // if (confirmType === 'messagebox') {
  //   try {
  //     await ElMessageBox.confirm(
  //       $t('ui.actionMessage.deleteConfirm', [row.name]),
  //       $t('ui.actionTitle.delete', ['']),
  //       {
  //         type: 'warning',
  //         confirmButtonText: $t('common.confirm'),
  //         cancelButtonText: $t('common.cancel'),
  //         confirmButtonClass: 'el-button--danger',
  //       },
  //     );
  //   } catch {
  //     // 用户点击取消，不做任何操作
  //     return;
  //   }
  // }

  const loadingMsg = ElMessage({
    message: $t('ui.actionMessage.deleting', [row.name]),
    duration: 0,
  });

  deleteMenu(row.id!)
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.name]));
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
    <FormDrawer @success="onRefresh" />
    <Grid>
      <template #toolbar-tools>
        <ElButton type="primary" @click="onCreate">
          {{ $t('ui.actionTitle.create', [$t('system.menu.menuManage')]) }}
        </ElButton>
      </template>
      <template #name="{ row }">
        <div class="flex w-full items-center gap-1">
          <div class="size-5 shrink-0">
            <IconifyIcon
              v-if="row.type === SystemMenuTypeEnum.BUTTON"
              icon="carbon:square-outline"
              class="size-full"
            />
            <IconifyIcon
              v-else-if="row.icon"
              :icon="row.icon || 'carbon:circle-dash'"
              class="size-full"
            />
            <IconifyIcon
              v-else
              icon="carbon:folder"
              class="size-full"
            />
          </div>
          <span class="flex-auto">{{ $t(row.name) }}</span>
        </div>
      </template>
    </Grid>
  </Page>
</template>
