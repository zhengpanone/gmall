<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type {
  OnActionClickParams,
  VxeTableGridOptions,
} from '#/adapter/vxe-table';
import type { SystemDictApi } from '#/api/system/dict';

import { computed, nextTick, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElButton, ElMessage } from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  deleteDict,
  deleteDictData,
  getDictDataPageList,
  getDictPageList,
} from '#/api/system/dict';

import { useDictDataColumns, useDictTypeColumns } from './data';
import DataForm from './modules/data-form.vue';
import Form from './modules/form.vue';

type PageLikeResponse<T> = Record<string, any> | T[];

const selectedDict = ref<SystemDictApi.Dict>();
const hasSelectedDict = computed(() => !!selectedDict.value?.id);

const [FormDrawer, formDrawerApi] = useVbenDrawer({
  connectedComponent: Form,
  destroyOnClose: true,
});

const [DataFormDrawer, dataFormDrawerApi] = useVbenDrawer({
  connectedComponent: DataForm,
  destroyOnClose: true,
});

const dictTypeQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'name',
    label: $t('system.dict.name'),
    componentProps: {
      clearable: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'type',
    label: $t('system.dict.type'),
    componentProps: {
      clearable: true,
    },
  },
];

const dictDataQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'label',
    label: $t('system.dict.dataLabel'),
    componentProps: {
      clearable: true,
    },
  },
];

const [DictTypeGrid, dictTypeGridApi] =
  useVbenVxeGrid<SystemDictApi.Dict>({
    formOptions: {
      actionButtonsReverse: true,
      actionWrapperClass: 'dict-form-actions',
      commonConfig: {
        formItemClass: 'min-w-56',
        labelWidth: 72,
      },
      compact: true,
      schema: dictTypeQuerySchema,
      showCollapseButton: true,
      wrapperClass: 'dict-search-grid',
    },
    gridEvents: {
      cellClick: (params: any) => {
        onDictTypeSelect(params.row as SystemDictApi.Dict);
      },
      checkboxChange: (params: any) => {
        onDictTypeSelect(params.row as SystemDictApi.Dict);
      },
    },
    gridOptions: {
      checkboxConfig: {
        highlight: true,
        range: false,
        trigger: 'row',
      },
      columns: useDictTypeColumns(onDictTypeActionClick),
      height: 'auto',
      keepSource: true,
      pagerConfig: { enabled: true, pageSize: 10 },
      proxyConfig: {
        ajax: {
          query: async ({ form, page }, formValues = {}) => {
            const currentPage = page?.currentPage ?? 1;
            const currentPageSize = page?.pageSize ?? 10;
            const queryForm = {
              ...form,
              ...formValues,
            } as Record<string, any>;
            const params: SystemDictApi.DictPageParam = {
              pageNo: currentPage,
              pageSize: currentPageSize,
            };

            if (queryForm.name?.trim()) {
              params.name = queryForm.name.trim();
              params.dictName = queryForm.name.trim();
            }
            if (queryForm.type?.trim()) {
              params.type = queryForm.type.trim();
              params.dictType = queryForm.type.trim();
            }

            const result = await getDictPageList(params).catch(() => []);
            const pageResult = normalizePageResult<SystemDictApi.Dict>(
              result,
              currentPage,
              currentPageSize,
              normalizeDictType,
              fallbackDictTypes,
            );

            if (
              !selectedDict.value &&
              pageResult.list.length > 0
            ) {
              nextTick(() => onDictTypeSelect(pageResult.list[0]));
            }

            return pageResult;
          },
        },
      },
      rowConfig: {
        isCurrent: true,
        keyField: 'id',
      },
      exportConfig: {},
      toolbarConfig: {
        custom: true,
        export: false,
        refresh: true,
        zoom: true,
      },
    } as VxeTableGridOptions<SystemDictApi.Dict>,
    tableTitle: $t('system.dict.typeList'),
  });

const [DictDataGrid, dictDataGridApi] =
  useVbenVxeGrid<SystemDictApi.DictData>({
    formOptions: {
      actionButtonsReverse: true,
      actionWrapperClass: 'dict-form-actions',
      commonConfig: {
        formItemClass: 'min-w-56',
        labelWidth: 72,
      },
      compact: true,
      schema: dictDataQuerySchema,
      showCollapseButton: true,
      wrapperClass: 'dict-search-grid dict-search-grid-single',
    },
    gridOptions: {
      checkboxConfig: {
        highlight: true,
        range: false,
      },
      columns: useDictDataColumns(onDictDataActionClick),
      height: 'auto',
      keepSource: true,
      pagerConfig: { enabled: true, pageSize: 10 },
      proxyConfig: {
        ajax: {
          query: async ({ form, page }, formValues = {}) => {
            const currentPage = page?.currentPage ?? 1;
            const currentPageSize = page?.pageSize ?? 10;
            const dict = selectedDict.value;

            if (!dict?.id) {
              return normalizePageResult<SystemDictApi.DictData>(
                [],
                currentPage,
                currentPageSize,
                normalizeDictData,
              );
            }

            const queryForm = {
              ...form,
              ...formValues,
            } as Record<string, any>;
            const params: SystemDictApi.DictDataPageParam = {
              dictId: dict.id,
              dictType: getDictType(dict),
              pageNo: currentPage,
              pageSize: currentPageSize,
            };

            if (queryForm.label?.trim()) {
              params.label = queryForm.label.trim();
            }

            const result = await getDictDataPageList(params).catch(() => []);
            return normalizePageResult<SystemDictApi.DictData>(
              result,
              currentPage,
              currentPageSize,
              normalizeDictData,
              getFallbackDictData(dict),
            );
          },
        },
      },
      rowConfig: { keyField: 'id' },
      exportConfig: {},
      toolbarConfig: {
        custom: true,
        export: false,
        refresh: true,
        zoom: true,
      },
    } as VxeTableGridOptions<SystemDictApi.DictData>,
    tableTitle: $t('system.dict.dataList'),
  });

function normalizePageResult<T>(
  response: PageLikeResponse<T>,
  currentPage: number,
  currentPageSize: number,
  normalizeItem: (item: Record<string, any>, index: number) => T,
  fallbackList: T[] = [],
) {
  const data = response ?? {};
  const rawList = Array.isArray(data)
    ? data
    : (data.list ??
      data.records ??
      data.rows ??
      data.items ??
      data.data?.list ??
      data.data?.records ??
      data.data?.items ??
      data.data?.rows ??
      []);
  const sourceList = (Array.isArray(rawList) ? rawList : fallbackList) as Array<
    Record<string, any>
  >;
  const list = sourceList.map(normalizeItem);
  const total = Number(
    Array.isArray(data)
      ? list.length
      : (data.total ?? data.data?.total ?? fallbackList.length),
  );
  const pageSize = Number(
    (Array.isArray(data) ? undefined : data.pageSize) ??
      (Array.isArray(data) ? undefined : data.size) ??
      currentPageSize,
  );
  const pageNum = Number(
    (Array.isArray(data) ? undefined : data.pageNum) ??
      (Array.isArray(data) ? undefined : data.pageNo) ??
      (Array.isArray(data) ? undefined : data.current) ??
      currentPage,
  );

  return {
    ...(!Array.isArray(data) ? data : {}),
    list,
    pageNum,
    pageSize,
    pages: Number(
      (!Array.isArray(data) ? data.pages : undefined) ??
        Math.ceil(total / (pageSize || 1)),
    ),
    total,
  };
}

function normalizeDictType(
  item: Record<string, any>,
  index: number,
): SystemDictApi.Dict {
  const type = item.type ?? item.dictType ?? item.code ?? item.dictCode;
  const name = item.name ?? item.dictName ?? item.label ?? type;

  return {
    ...item,
    code: String(item.code ?? item.dictCode ?? type ?? ''),
    createTime: formatTime(item.createTime ?? item.createdTime),
    id: item.id ?? item.dictId ?? type ?? index,
    name: String(name ?? ''),
    remark: item.remark ?? item.description ?? '',
    sort: Number(item.sort ?? index),
    status: Number(item.status ?? 1),
    type: String(type ?? ''),
  };
}

function normalizeDictData(
  item: Record<string, any>,
  index: number,
): SystemDictApi.DictData {
  const value =
    item.value ?? item.dictValue ?? item.itemValue ?? item.itemCode ?? '';

  return {
    ...item,
    createTime: formatTime(item.createTime ?? item.createdTime),
    id: item.id ?? `${item.dictId ?? 'dict'}-${value || index}`,
    label: String(item.label ?? item.dictLabel ?? item.name ?? value),
    remark: item.remark ?? '',
    sort: Number(item.sort ?? item.dataSort ?? 0),
    status: Number(item.status ?? 1),
    value: String(value),
  };
}

function formatTime(value: unknown): string {
  if (Array.isArray(value)) {
    const [year, month, day, hour = 0, minute = 0, second = 0] = value;
    if (year && month && day) {
      return `${year}-${pad(month)}-${pad(day)} ${pad(hour)}:${pad(minute)}:${pad(second)}`;
    }
  }

  return value === undefined || value === null ? '' : String(value);
}

function pad(value: unknown) {
  return String(value).padStart(2, '0');
}

function getDictType(dict?: SystemDictApi.Dict) {
  return String(dict?.type ?? dict?.code ?? dict?.dictType ?? '');
}

function onDictTypeActionClick({
  code,
  row,
}: OnActionClickParams<SystemDictApi.Dict>) {
  switch (code) {
    case 'delete': {
      onDictTypeDelete(row);
      break;
    }
    case 'edit': {
      onDictTypeEdit(row);
      break;
    }
  }
}

function onDictDataActionClick({
  code,
  row,
}: OnActionClickParams<SystemDictApi.DictData>) {
  switch (code) {
    case 'delete': {
      onDictDataDelete(row);
      break;
    }
    case 'edit': {
      onDictDataEdit(row);
      break;
    }
  }
}

function onDictTypeSelect(row?: SystemDictApi.Dict) {
  if (!row?.id || row.id === selectedDict.value?.id) {
    return;
  }

  selectedDict.value = row;
  (dictTypeGridApi.grid as any)?.setCurrentRow?.(row);
  (dictTypeGridApi.grid as any)?.setAllCheckboxRow?.(false);
  (dictTypeGridApi.grid as any)?.setCheckboxRow?.(row, true);
  dictDataGridApi.query();
}

function onDictTypeRefresh() {
  dictTypeGridApi.query();
}

function onDictDataRefresh() {
  dictDataGridApi.query();
}

function onDictTypeExport() {
  (dictTypeGridApi.grid as any)?.openExport?.({ type: 'csv' });
}

function onDictDataExport() {
  (dictDataGridApi.grid as any)?.openExport?.({ type: 'csv' });
}

function onDictTypeEdit(row: SystemDictApi.Dict) {
  formDrawerApi.setData(row).open();
}

function onDictTypeCreate() {
  formDrawerApi.setData({} as SystemDictApi.Dict).open();
}

function onDictDataEdit(row: SystemDictApi.DictData) {
  dataFormDrawerApi
    .setData({
      dict: selectedDict.value,
      record: row,
    })
    .open();
}

function onDictDataCreate() {
  if (!selectedDict.value) {
    ElMessage.warning($t('system.dict.selectTypeFirst'));
    return;
  }

  dataFormDrawerApi
    .setData({
      dict: selectedDict.value,
      record: {},
    })
    .open();
}

function onDictTypeDelete(row: SystemDictApi.Dict) {
  if (!row.id) {
    return;
  }

  const loadingMsg = ElMessage({
    duration: 0,
    message: $t('ui.actionMessage.deleting', [row.name]),
  });

  deleteDict([row.id])
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.name]));
      if (selectedDict.value?.id === row.id) {
        selectedDict.value = undefined;
        dictDataGridApi.query();
      }
      onDictTypeRefresh();
    })
    .finally(() => {
      loadingMsg.close();
    });
}

function onDictDataDelete(row: SystemDictApi.DictData) {
  if (!row.id) {
    return;
  }

  const loadingMsg = ElMessage({
    duration: 0,
    message: $t('ui.actionMessage.deleting', [row.label]),
  });

  deleteDictData([row.id])
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.label]));
      onDictDataRefresh();
    })
    .finally(() => {
      loadingMsg.close();
    });
}

const fallbackDictTypes: SystemDictApi.Dict[] = [
  {
    code: 'sys_user_sex',
    createTime: '1770066890000',
    id: 'sys_user_sex',
    name: '用户性别',
    remark: '用户性别列表',
    status: 1,
    type: 'sys_user_sex',
  },
  {
    code: 'sys_show_hide',
    createTime: '1770066890000',
    id: 'sys_show_hide',
    name: '菜单状态',
    remark: '菜单状态列表',
    status: 1,
    type: 'sys_show_hide',
  },
  {
    code: 'sys_normal_disable',
    createTime: '1770066890000',
    id: 'sys_normal_disable',
    name: '系统开关',
    remark: '系统开关列表',
    status: 1,
    type: 'sys_normal_disable',
  },
  {
    code: 'sys_yes_no',
    createTime: '1770066890000',
    id: 'sys_yes_no',
    name: '系统是否',
    remark: '系统是否列表',
    status: 1,
    type: 'sys_yes_no',
  },
  {
    code: 'sys_notice_type',
    createTime: '1770066890000',
    id: 'sys_notice_type',
    name: '通知类型',
    remark: '通知类型列表',
    status: 1,
    type: 'sys_notice_type',
  },
  {
    code: 'sys_notice_status',
    createTime: '1770066890000',
    id: 'sys_notice_status',
    name: '通知状态',
    remark: '通知状态列表',
    status: 1,
    type: 'sys_notice_status',
  },
  {
    code: 'sys_oper_type',
    createTime: '1770066890000',
    id: 'sys_oper_type',
    name: '操作类型',
    remark: '操作类型列表',
    status: 1,
    type: 'sys_oper_type',
  },
  {
    code: 'sys_common_status',
    createTime: '1770066890000',
    id: 'sys_common_status',
    name: '系统状态',
    remark: '登录状态列表',
    status: 1,
    type: 'sys_common_status',
  },
  {
    code: 'sys_grant_type',
    createTime: '1770066890000',
    id: 'sys_grant_type',
    name: '授权类型',
    remark: '认证授权类型',
    status: 1,
    type: 'sys_grant_type',
  },
  {
    code: 'sys_device_type',
    createTime: '1770066890000',
    id: 'sys_device_type',
    name: '设备类型',
    remark: '客户端设备类型',
    status: 1,
    type: 'sys_device_type',
  },
];

const fallbackDictDataMap: Record<string, SystemDictApi.DictData[]> = {
  sys_grant_type: [
    {
      createTime: '1770066891000',
      id: 'password',
      label: '密码认证',
      remark: '密码认证',
      sort: 0,
      status: 1,
      value: 'password',
    },
    {
      createTime: '1770066891000',
      id: 'sms',
      label: '短信认证',
      remark: '短信认证',
      sort: 0,
      status: 1,
      value: 'sms',
    },
    {
      createTime: '1770066891000',
      id: 'email',
      label: '邮件认证',
      remark: '邮件认证',
      sort: 0,
      status: 1,
      value: 'email',
    },
    {
      createTime: '1770066891000',
      id: 'xcx',
      label: '小程序认证',
      remark: '小程序认证',
      sort: 0,
      status: 1,
      value: 'xcx',
    },
    {
      createTime: '1770066891000',
      id: 'social',
      label: '三方登录认证',
      remark: '三方登录认证',
      sort: 0,
      status: 1,
      value: 'social',
    },
  ],
  sys_device_type: [
    {
      createTime: '1770066891000',
      id: 'pc',
      label: 'PC',
      remark: 'PC',
      sort: 0,
      status: 1,
      value: 'pc',
    },
    {
      createTime: '1770066892000',
      id: 'android',
      label: '安卓',
      remark: '安卓',
      sort: 0,
      status: 1,
      value: 'android',
    },
    {
      createTime: '1770066892000',
      id: 'ios',
      label: 'iOS',
      remark: 'iOS',
      sort: 0,
      status: 1,
      value: 'ios',
    },
    {
      createTime: '1770066892000',
      id: 'miniapp',
      label: '小程序',
      remark: '小程序',
      sort: 0,
      status: 1,
      value: 'xcx',
    },
    {
      createTime: '1772024193000',
      id: 'chat',
      label: '对话',
      remark: '',
      sort: 0,
      status: 1,
      value: 'chat',
    },
  ],
};

function getFallbackDictData(dict: SystemDictApi.Dict) {
  const type = getDictType(dict);
  return fallbackDictDataMap[type] ?? fallbackDictDataMap.sys_grant_type ?? [];
}
</script>

<template>
  <Page auto-content-height content-class="dict-page">
    <FormDrawer @success="onDictTypeRefresh" />
    <DataFormDrawer @success="onDictDataRefresh" />

    <div class="dict-layout">
      <DictTypeGrid class="dict-panel">
        <template #toolbar-tools>
          <ElButton size="small" @click="onDictTypeRefresh">
            {{ $t('common.refresh') }}
          </ElButton>
          <ElButton size="small" @click="onDictTypeExport">
            {{ $t('system.dict.export') }}
          </ElButton>
          <ElButton disabled size="small" type="danger">
            {{ $t('common.delete') }}
          </ElButton>
          <ElButton size="small" type="primary" @click="onDictTypeCreate">
            {{ $t('common.create') }}
          </ElButton>
        </template>
      </DictTypeGrid>

      <DictDataGrid class="dict-panel">
        <template #toolbar-tools>
          <ElButton size="small" @click="onDictDataExport">
            {{ $t('system.dict.export') }}
          </ElButton>
          <ElButton disabled size="small" type="danger">
            {{ $t('common.delete') }}
          </ElButton>
          <ElButton
            :disabled="!hasSelectedDict"
            size="small"
            type="primary"
            @click="onDictDataCreate"
          >
            {{ $t('common.create') }}
          </ElButton>
        </template>

        <template #dictDataLabel="{ row }">
          <ElButton
            v-if="row.label === '对话'"
            link
            size="small"
            type="success"
          >
            {{ row.label }}
          </ElButton>
          <span v-else class="dict-tag">{{ row.label }}</span>
        </template>
      </DictDataGrid>
    </div>
  </Page>
</template>

<style scoped>
:deep(.dict-page) {
  padding: 8px;
}

.dict-layout {
  display: grid;
  grid-template-columns: minmax(480px, 1fr) minmax(560px, 1fr);
  gap: 16px;
  height: 100%;
  min-height: 0;
}

.dict-panel {
  min-width: 0;
}

:deep(.dict-panel > .vxe-grid),
:deep(.dict-panel .vxe-grid--layout-wrapper) {
  height: 100%;
}

:deep(.dict-panel .vxe-toolbar) {
  min-height: 44px;
}

:deep(.dict-panel .vxe-grid--layout-body-content-wrapper) {
  min-height: 0;
}

:deep(.dict-search-grid) {
  display: grid;
  grid-template-columns: repeat(2, minmax(180px, 1fr)) max-content;
  align-items: start;
  gap: 8px 16px;
}

:deep(.dict-search-grid-single) {
  grid-template-columns: minmax(220px, 1fr) max-content;
}

:deep(.dict-form-actions) {
  align-self: start;
  min-width: 220px;
  padding-bottom: 0;
}

.dict-tag {
  display: inline-flex;
  align-items: center;
  max-width: 100%;
  min-height: 24px;
  padding: 0 8px;
  overflow: hidden;
  color: hsl(var(--foreground));
  text-overflow: ellipsis;
  white-space: nowrap;
  background-color: hsl(var(--accent));
  border: 1px solid hsl(var(--border));
  border-radius: 4px;
}

@media (max-width: 1200px) {
  .dict-layout {
    grid-template-columns: 1fr;
  }
}
</style>
