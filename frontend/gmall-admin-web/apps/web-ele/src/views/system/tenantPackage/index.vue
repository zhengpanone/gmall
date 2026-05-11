<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { OnActionClickParams, VxeTableGridOptions } from '#/adapter/vxe-table';
import type { TenantPackage, TenantPackagePageParam } from '#/api/system/tenantPackage/model';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage, ElMessageBox } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import { deleteTenantPackage, getTenantPackagePageList } from '#/api/system/tenantPackage';

import { useColumns } from './data';
import Form from './modules/form.vue';

const [FormDrawer, formDrawerApi] = useVbenDrawer({
  connectedComponent: Form,
  destroyOnClose: true,
});

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
];

function normalizeRolePageResult(response: any, currentPage: number, currentPageSize: number) {
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
  const total = Number(data.total ?? data.totalCount ?? data?.data?.total ?? list.length ?? 0);
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
          const params: TenantPackagePageParam = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };
          // formOptions 模式下筛选值来自 query 的第2个参数；这里兼容两种来源
          const queryForm = {
            ...form,
            ...formValues,
          } as Record<string, any>;

          if (queryForm.packageName?.trim()) params.packageName = queryForm.packageName.trim();
          if (queryForm.packageCode?.trim()) params.packageCode = queryForm.packageCode.trim();
          const result = await getTenantPackagePageList(params);

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

function onActionClick({ code, row }: OnActionClickParams<TenantPackage>) {
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

function onEdit(row: TenantPackage) {
  formDrawerApi.setData(row).open();
}

function onCreate() {
  formDrawerApi.setData({} as TenantPackage).open();
}

function onDelete(row: TenantPackage) {
  if (!row.id) return;

  const loadingMsg = ElMessage({
    message: $t('ui.actionMessage.deleting', [row.packageName]),
    duration: 0,
  });
  deleteTenantPackage([row.id])
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.packageName]));
      onRefresh();
    })
    .catch(() => {
      // 接口错误信息已被全局拦截器统一处理，此处无需额外操作
    })
    .finally(() => {
      loadingMsg.close();
    });
}

function getSelectedRoleRows() {
  const selectedRows = (gridApi.grid as any)?.getCheckboxRecords?.();
  return Array.isArray(selectedRows) ? selectedRows : [];
}

async function onBatchDelete() {
  const selectedRows = getSelectedRoleRows();
  const ids = selectedRows
    .map((item) => item?.id)
    .filter((id): id is number | string => id !== undefined && id !== null);

  if (ids.length === 0) {
    ElMessage.warning($t('system.role.selectToDelete'));
    return;
  }

  try {
    await ElMessageBox.confirm(
      $t('ui.actionMessage.deleteConfirm', [`${ids.length} ${$t('system.role.name')}`]),
      $t('ui.actionTitle.delete', ['']),
      {
        type: 'warning',
        confirmButtonText: $t('common.confirm'),
        cancelButtonText: $t('common.cancel'),
        confirmButtonClass: 'el-button--danger',
      },
    );
  } catch {
    return;
  }

  const loadingMsg = ElMessage({
    message: $t('ui.actionMessage.deleting', [`${ids.length} ${$t('system.role.name')}`]),
    duration: 0,
  });

  deleteTenantPackage(ids)
    .then(() => {
      ElMessage.success($t('system.role.batchDeleteSuccess', [ids.length]));
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
</script>

<template>
  <Page auto-content-height>
    <FormDrawer @success="onRefresh" />
    <Grid>
      <template #toolbar-tools>
        <ElButton type="primary" @click="onCreate">
          {{ $t('ui.actionTitle.create', [$t('system.role.roleManage')]) }}
        </ElButton>
        <ElButton type="danger" @click="onBatchDelete">
          {{ $t('system.role.batchDelete') }}
        </ElButton>
      </template>
    </Grid>
  </Page>
</template>
