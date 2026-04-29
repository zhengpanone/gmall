<script lang="ts" setup>
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

function normalizeRolePageResult(
  response: any,
  currentPage: number,
  currentPageSize: number,
) {
  const data = response ?? {};
  console.log('接口返回的原始数据：', data);
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
              options: roleTypeOptions,
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
          const currentPage = page?.currentPage ?? 1;
          const currentPageSize = page?.pageSize ?? 20;
          const params: SystemRoleApi.RolePageParam = {
            pageNo: currentPage,
            pageSize: currentPageSize,
          };
          // 兼容不同后端分页参数命名，避免接口有数据但表格拿不到
          const compatibleParams = {
            ...params,
            current: currentPage,
            page: currentPage,
            pageNum: currentPage,
            size: currentPageSize,
          };
          if (form?.roleName?.trim()) params.roleName = form.roleName.trim();
          if (form?.roleCode?.trim()) params.roleCode = form.roleCode.trim();
          if (form?.roleType !== undefined) params.roleType = form.roleType;
          const result = await getRolePageList({
            ...compatibleParams,
            roleCode: params.roleCode,
            roleName: params.roleName,
            roleType: params.roleType,
          });

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
