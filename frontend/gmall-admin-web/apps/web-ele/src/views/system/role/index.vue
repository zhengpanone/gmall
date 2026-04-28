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
import { deleteRole, getRolePageList } from '#/api/system/role';

import { useColumns } from './data';
import Form from './modules/form.vue';

const [FormDrawer, formDrawerApi] = useVbenDrawer({
  connectedComponent: Form,
  destroyOnClose: true,
});

const [Grid, gridApi] = useVbenVxeGrid({
  gridOptions: {
    columns: useColumns(onActionClick),
    formConfig: {
      enabled: true,
      items: [
        {
          field: 'roleName',
          title: $t('system.role.name'),
          span: 6,
          itemRender: {
            name: 'ElInput',
            props: {
              placeholder: $t('system.role.name'),
              clearable: true,
            },
          },
        },
        {
          field: 'roleCode',
          title: $t('system.role.code'),
          span: 6,
          itemRender: {
            name: 'ElInput',
            props: {
              placeholder: $t('system.role.code'),
              clearable: true,
            },
          },
        },
        {
          field: 'roleType',
          title: $t('system.role.type'),
          span: 6,
          itemRender: {
            name: 'ElSelect',
            props: {
              placeholder: $t('system.role.type'),
              clearable: true,
              options: [
                { label: '系统内置', value: 1 },
                { label: '自定义', value: 2 },
              ],
            },
          },
        },
        {
          span: 6,
          itemRender: {
            name: 'VxeFormItemButton',
            children: [
              { type: 'submit', content: $t('common.search') },
              { type: 'reset', content: $t('common.reset') },
            ],
          },
        },
      ],
    },
    height: 'auto',
    keepSource: true,
    pagerConfig: { enabled: true },
    proxyConfig: {
      ajax: {
        query: async ({ form, page }) => {
          const params: SystemRoleApi.RolePageParam = {
            pageNo: page.currentPage,
            pageSize: page.pageSize,
          };
          if (form?.roleName) params.roleName = form.roleName;
          if (form?.roleCode) params.roleCode = form.roleCode;
          if (form?.roleType !== undefined) params.roleType = form.roleType;
          return await getRolePageList(params);
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
  const loadingMsg = ElMessage({
    message: $t('ui.actionMessage.deleting', [row.roleName]),
    duration: 0,
  });
  deleteRole(row.id!)
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.roleName]));
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
          {{ $t('ui.actionTitle.create', [$t('system.role.roleManage')]) }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
