<script lang="ts" setup>
import type { FormInstance, FormRules } from 'element-plus';

import type { VbenFormSchema } from '#/adapter/form';
import type { OnActionClickParams, VxeTableGridOptions } from '#/adapter/vxe-table';
import type { SystemDictApi } from '#/api/system/dict';

import { computed, nextTick, ref } from 'vue';

import { Page, useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import {
  ElButton,
  ElDialog,
  ElForm,
  ElFormItem,
  ElInput,
  ElInputNumber,
  ElMessage,
  ElOption,
  ElRadio,
  ElRadioGroup,
  ElSelect,
  ElTooltip,
} from 'element-plus';

import { useVbenVxeGrid } from '#/adapter/vxe-table';
import {
  createDictType,
  deleteDictData,
  deleteDictType,
  getDictDataList,
  getDictTypePageList,
  updateDictType,
} from '#/api/system/dict';

import { useDictDataColumns, useDictTypeColumns } from './data';
import DataForm from './modules/data-form.vue';

const selectedDict = ref<SystemDictApi.DictType>();
const hasSelectedDict = computed(() => !!selectedDict.value?.id);

type DictTypeFormModel = {
  id: string;
  remark: string;
  sort: number;
  status: number;
  type: number | string;
  typeCode: string;
  typeName: string;
};

const defaultDictTypeForm = (): DictTypeFormModel => ({
  id: '',
  typeCode: '',
  typeName: '',
  type: 1,
  sort: 1,
  status: 1,
  remark: '',
});

const dictTypeDialogVisible = ref(false);
const dictTypeSubmitting = ref(false);
const editingDictType = ref<SystemDictApi.DictType>();
const dictTypeFormRef = ref<FormInstance>();
const dictTypeForm = ref<DictTypeFormModel>(defaultDictTypeForm());
const dictTypeDialogTitle = computed(() =>
  editingDictType.value?.id
    ? $t('page.common.editItem', [$t('system.dict.dictManage')])
    : $t('page.common.createItem', [$t('system.dict.dictManage')]),
);

const dictTypeFormRules: FormRules<DictTypeFormModel> = {
  typeName: [{ required: true, message: '请输入字典名称', trigger: 'blur' }],
  typeCode: [{ required: true, message: '请输入字典编码', trigger: 'blur' }],
  type: [{ required: true, message: '请选择类型', trigger: 'change' }],
};

const dictCategoryOptions = [
  { label: $t('system.dict.systemType'), value: 1 },
  { label: $t('system.dict.businessType'), value: 2 },
];

const [DataFormDrawer, dataFormDrawerApi] = useVbenDrawer({
  connectedComponent: DataForm,
  destroyOnClose: true,
});

const dictTypeQuerySchema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'dictTypeName',
    label: $t('system.dict.typeName'),
    componentProps: {
      clearable: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'dictTypeCode',
    label: $t('system.dict.typeCode'),
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

const [DictTypeGrid, dictTypeGridApi] = useVbenVxeGrid<SystemDictApi.DictType>({
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
      onDictTypeSelect(params.row as SystemDictApi.DictType);
    },
    checkboxChange: (params: any) => {
      onDictTypeSelect(params.row as SystemDictApi.DictType);
    },
    currentRowChange: (params: any) => {
      onDictTypeSelect(
        (params.row ??
          params.newRow ??
          params.currentRow ??
          params.newValue) as SystemDictApi.DictType,
      );
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

          const queryName = queryForm.dictTypeName?.trim() ?? queryForm.name?.trim();
          const queryCode = queryForm.dictTypeCode?.trim() ?? queryForm.type?.trim();

          if (queryName) {
            params.name = queryName;
            params.dictName = queryName;
          }
          if (queryCode) {
            params.code = queryCode;
            params.dictCode = queryCode;
          }

          const result = await getDictTypePageList(params).catch(() => {
            return {
              list: [],
              total: 0,
            };
          });

          if (!selectedDict.value && result.list.length > 0) {
            nextTick(() => onDictTypeSelect(result.list[0]));
          }

          return result;
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
  } as VxeTableGridOptions<SystemDictApi.DictType>,
  tableTitle: $t('system.dict.typeList'),
});

const [DictDataGrid, dictDataGridApi] = useVbenVxeGrid<SystemDictApi.DictData>({
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
    pagerConfig: { enabled: false },
    proxyConfig: {
      ajax: {
        query: async ({ form }, formValues = {}) => {
          const dict = selectedDict.value;

          const queryForm = {
            ...form,
            ...formValues,
          } as Record<string, any>;
          const keyword = String(queryForm.label ?? '').trim();

          const params: SystemDictApi.DictDataListParam = {
            typeId: dict?.id,
            typeCode: dict?.typeCode,
          };

          if (keyword) {
            params.dataName = keyword;
          }

          const result = await getDictDataList(params).catch(() => {
            return {
              data: [],
            };
          });
          return result.data;
        },
      },
    },
    rowConfig: { keyField: 'id' },
    treeConfig: {
      parentField: 'parentId',
      rowField: 'id',
      transform: true,
    },
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

function getDictId(dict?: SystemDictApi.DictType) {
  return dict?.id ?? (dict as Record<string, any> | undefined)?.dictId;
}

function getDictTypeCode(dict?: SystemDictApi.DictType) {
  const raw =
    dict?.typeCode ??
    (dict as Record<string, any> | undefined)?.dictType ??
    (dict as Record<string, any> | undefined)?.code ??
    '';
  return raw === undefined || raw === null ? '' : String(raw);
}

function getDictCategory(dict?: SystemDictApi.DictType) {
  return String(dict?.type ?? (dict as Record<string, any> | undefined)?.categoryType ?? 1);
}

function onDictTypeActionClick({ code, row }: OnActionClickParams<SystemDictApi.DictType>) {
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

function onDictDataActionClick({ code, row }: OnActionClickParams<SystemDictApi.DictData>) {
  switch (code) {
    case 'append': {
      onDictDataCreate(row);
      break;
    }
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

function onDictTypeSelect(row?: SystemDictApi.DictType) {
  if (!row) {
    return;
  }

  const normalizedRow: SystemDictApi.DictType = {
    ...row,
    id: getDictId(row),
  };

  if (!normalizedRow.id && !getDictTypeCode(normalizedRow)) {
    return;
  }

  selectedDict.value = normalizedRow;
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

function resetDictTypeForm() {
  editingDictType.value = undefined;
  dictTypeForm.value = defaultDictTypeForm();
  dictTypeFormRef.value?.clearValidate();
}

function openDictTypeDialog(row?: SystemDictApi.DictType) {
  if (row?.id) {
    editingDictType.value = row;
    dictTypeForm.value = {
      id: String(row.id ?? ''),
      typeName: String(row.typeName ?? ''),
      typeCode: String(row.typeCode ?? ''),
      type: getDictCategory(row),
      sort: Number(row.sort ?? 0),
      status: Number(row.status ?? 1),
      remark: String(row.remark ?? ''),
    };
    dictTypeFormRef.value?.clearValidate();
  } else {
    resetDictTypeForm();
  }

  dictTypeDialogVisible.value = true;
}

async function onDictTypeSubmit() {
  if (dictTypeSubmitting.value) {
    return;
  }

  const valid = await dictTypeFormRef.value?.validate().catch(() => false);
  if (!valid) {
    return;
  }

  const payload = {
    ...editingDictType.value,
    ...dictTypeForm.value,
    typeCode: dictTypeForm.value.typeCode.trim(),
    type: dictTypeForm.value.type,
  };

  dictTypeSubmitting.value = true;
  try {
    if (editingDictType.value?.id) {
      await updateDictType(payload as SystemDictApi.UpdateDictTypeParams);
      ElMessage.success($t('page.common.editSuccess'));
    } else {
      await createDictType(payload as SystemDictApi.CreateDictTypeParams);
      ElMessage.success($t('page.common.createSuccess'));
    }
    dictTypeDialogVisible.value = false;
    resetDictTypeForm();
    onDictTypeRefresh();
  } finally {
    dictTypeSubmitting.value = false;
  }
}

function onDictTypeEdit(row: SystemDictApi.DictType) {
  openDictTypeDialog(row);
}

function onDictTypeCreate() {
  openDictTypeDialog();
}

function onDictDataEdit(row: SystemDictApi.DictData) {
  dataFormDrawerApi
    .setData({
      dict: selectedDict.value,
      record: row,
    })
    .open();
}

function onDictDataCreate(parentRow?: Partial<SystemDictApi.DictData>) {
  if (!selectedDict.value) {
    ElMessage.warning($t('system.dict.selectTypeFirst'));
    return;
  }

  dataFormDrawerApi
    .setData({
      dict: selectedDict.value,
      record: parentRow?.id
        ? {
            parentId: parentRow.id,
          }
        : {},
    })
    .open();
}

function onDictTypeDelete(row: SystemDictApi.DictType) {
  if (!row.id) {
    return;
  }

  const loadingMsg = ElMessage({
    duration: 0,
    message: $t('ui.actionMessage.deleting', [row.typeName]),
  });

  deleteDictType([row.id])
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.typeName]));
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
    message: $t('ui.actionMessage.deleting', [row.dataName ?? '']),
  });

  deleteDictData([row.id])
    .then(() => {
      ElMessage.success($t('ui.actionMessage.deleteSuccess', [row.dataName ?? '']));
      onDictDataRefresh();
    })
    .finally(() => {
      loadingMsg.close();
    });
}
</script>

<template>
  <Page auto-content-height content-class="dict-page">
    <DataFormDrawer @success="onDictDataRefresh" />
    <ElDialog
      v-model="dictTypeDialogVisible"
      :close-on-click-modal="false"
      :title="dictTypeDialogTitle"
      align-center
      destroy-on-close
      width="640px"
      @closed="resetDictTypeForm"
    >
      <ElForm
        ref="dictTypeFormRef"
        :model="dictTypeForm"
        :rules="dictTypeFormRules"
        label-position="left"
        label-width="88px"
      >
        <ElFormItem prop="typeCode">
          <template #label>
            <span class="dict-field-label">
              <span>{{ $t('system.dict.typeCode') }}</span>
              <ElTooltip :content="$t('system.dict.typeCodeTip')" placement="top">
                <span class="dict-field-tip">?</span>
              </ElTooltip>
            </span>
          </template>
          <ElInput v-model="dictTypeForm.typeCode" clearable placeholder="请输入" />
        </ElFormItem>
        <ElFormItem :label="$t('system.dict.typeName')" prop="typeName">
          <ElInput v-model="dictTypeForm.typeName" clearable placeholder="请输入" />
        </ElFormItem>
        <ElFormItem :label="$t('system.dict.type')" prop="type">
          <ElSelect v-model="dictTypeForm.type" class="w-full" placeholder="请选择">
            <ElOption
              v-for="item in dictCategoryOptions"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            />
          </ElSelect>
        </ElFormItem>
        <ElFormItem :label="$t('system.dict.sort')" prop="sort">
          <ElInputNumber
            v-model="dictTypeForm.sort"
            :min="0"
            class="w-full"
            controls-position="right"
          />
        </ElFormItem>
        <ElFormItem :label="$t('system.dict.status')" prop="status">
          <ElRadioGroup v-model="dictTypeForm.status">
            <ElRadio :value="1">{{ $t('common.enabled') }}</ElRadio>
            <ElRadio :value="0">{{ $t('common.disabled') }}</ElRadio>
          </ElRadioGroup>
        </ElFormItem>
        <ElFormItem :label="$t('system.dict.remark')" prop="remark">
          <ElInput v-model="dictTypeForm.remark" :rows="3" placeholder="请输入" type="textarea" />
        </ElFormItem>
      </ElForm>
      <template #footer>
        <ElButton @click="dictTypeDialogVisible = false">
          {{ $t('common.cancel') }}
        </ElButton>
        <ElButton :loading="dictTypeSubmitting" type="primary" @click="onDictTypeSubmit">
          {{ $t('common.confirm') }}
        </ElButton>
      </template>
    </ElDialog>

    <div class="dict-layout">
      <DictTypeGrid class="dict-panel">
        <template #toolbar-tools>
          <ElButton @click="onDictTypeRefresh">
            {{ $t('common.refresh') }}
          </ElButton>
          <ElButton @click="onDictTypeExport">
            {{ $t('system.dict.export') }}
          </ElButton>
          <ElButton disabled type="danger">
            {{ $t('common.delete') }}
          </ElButton>
          <ElButton type="primary" @click="onDictTypeCreate">
            {{ $t('common.create') }}
          </ElButton>
        </template>
      </DictTypeGrid>

      <DictDataGrid class="dict-panel">
        <template #toolbar-tools>
          <ElButton @click="onDictDataExport">
            {{ $t('system.dict.export') }}
          </ElButton>
          <ElButton disabled type="danger">
            {{ $t('common.delete') }}
          </ElButton>
          <ElButton :disabled="!hasSelectedDict" type="primary" @click="onDictDataCreate()">
            {{ $t('common.create') }}
          </ElButton>
        </template>

        <template #dictDataLabel="{ row }">
          <ElButton v-if="row.dataName === '对话'" link type="success">
            {{ row.dataName }}
          </ElButton>
          <span v-else class="dict-tag">{{ row.dataName }}</span>
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
  gap: 8px 16px;
  align-items: start;
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
  text-overflow: ellipsis;
  color: hsl(var(--foreground));
  white-space: nowrap;
  background-color: hsl(var(--accent));
  border: 1px solid hsl(var(--border));
  border-radius: 4px;
}

.dict-field-label {
  display: inline-flex;
  gap: 6px;
  align-items: center;
}

.dict-field-tip {
  display: inline-flex;
  align-items: center;
  justify-content: center;
  width: 16px;
  height: 16px;
  font-size: 12px;
  font-weight: 600;
  line-height: 1;
  color: hsl(var(--foreground));
  cursor: help;
  border: 1px solid hsl(var(--border));
  border-radius: 50%;
  transform: translateY(1px);
}

@media (max-width: 1200px) {
  .dict-layout {
    grid-template-columns: 1fr;
  }
}
</style>
