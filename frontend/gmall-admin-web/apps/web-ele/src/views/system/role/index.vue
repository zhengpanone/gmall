<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteRole, getRolePageList, SystemRoleApi } from '#/api/system/role';

import { useColumns } from './data';
import Form from './modules/form.vue';

const [FormDrawer, formDrawerApi] = useVbenDrawer({
  connectedComponent: Form,
  destroyOnClose: true,
});

const roleTypeOptions = [
  { label: $t('system.role.type1'), value: SystemRoleApi.RoleTypeEnum.SYSTEM },
  { label: $t('system.role.type2'), value: SystemRoleApi.RoleTypeEnum.CUSTOM },
];

const roleQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'roleName',
    label: $t('system.role.name'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.role.name'),
    },
  },
  {
    component: 'Input',
    fieldName: 'roleCode',
    label: $t('system.role.code'),
    componentProps: {
      clearable: true,
      placeholder: $t('system.role.code'),
    },
  },
  {
    component: 'Select',
    fieldName: 'roleType',
    label: $t('system.role.type'),
    componentProps: {
      clearable: true,
      options: roleTypeOptions,
      placeholder: $t('system.role.type'),
    },
  },
];

function normalizeRolePageResult(
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

const [Grid, gridApi] = useVbenVxeGrid({
  formOptions: {
    schema: roleQuerySchema,
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
          const params: SystemRoleApi.RolePageParam = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };
          // formOptions 模式下筛选值来自 query 的第2个参数；这里兼容两种来源
          const queryForm = {
            ...form,
            ...formValues,
          } as Record<string, any>;

          if (queryForm.roleName?.trim()) params.roleName = queryForm.roleName.trim();
          if (queryForm.roleCode?.trim()) params.roleCode = queryForm.roleCode.trim();
          if (queryForm.roleType !== undefined && queryForm.roleType !== null) {
            params.roleType = queryForm.roleType;
          }
          const result = await getRolePageList(params);

          return normalizeRolePageResult(result, currentPage, currentPageSize);
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
  if (!row.id) return;

  const loadingMsg = ElMessage({
    message: $t('ui.actionMessage.deleting', [row.roleName]),
    duration: 0,
  });
  deleteRole(row.id)
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
