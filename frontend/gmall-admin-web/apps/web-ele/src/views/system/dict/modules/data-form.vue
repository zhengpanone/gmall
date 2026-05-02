<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { SystemDictApi } from '#/api/system/dict';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { createDictData, getDictDataList, updateDictData } from '#/api/system/dict';

const emit = defineEmits<{
  success: [];
}>();

interface DrawerData {
  dict?: SystemDictApi.DictType;
  record?: Partial<SystemDictApi.DictData>;
}

const drawerData = ref<DrawerData>({});
const isEdit = computed(() => !!drawerData.value.record?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'typeName',
    label: $t('system.dict.typeName'),
    componentProps: {
      disabled: true,
      readonly: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'typeCode',
    label: $t('system.dict.typeCode'),
    componentProps: {
      disabled: true,
      readonly: true,
    },
  },
  {
    component: 'TreeSelect',
    fieldName: 'parentId',
    label: '上级字典数据',
    componentProps: {
      api: getParentOptions,
      dataFields: {
        children: 'children',
        label: 'dataName',
        value: 'id',
      },
      placeholder: '请选择上级字典数据',
      allowClear: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'dataName',
    label: $t('system.dict.dataLabel'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'dataCode',
    label: $t('system.dict.dataValue'),
    rules: 'required',
  },
  {
    component: 'InputNumber',
    fieldName: 'sort',
    label: $t('system.dict.dataSort'),
    defaultValue: 1,
    componentProps: {
      min: 0,
      style: { width: '100%' },
    },
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.dict.status'),
    defaultValue: 1,
    componentProps: {
      options: [
        { label: $t('common.enabled'), value: 1 },
        { label: $t('common.disabled'), value: 0 },
      ],
    },
  },
  {
    component: 'Input',
    fieldName: 'remark',
    label: $t('system.dict.remark'),
    componentProps: {
      rows: 3,
      type: 'textarea',
    },
  },
];

const [Form, formApi] = useVbenForm({
  commonConfig: {
    colon: true,
    formItemClass: 'col-span-1',
  },
  schema,
  showDefaultActions: false,
  wrapperClass: 'grid-cols-1 gap-x-4',
});

const [Drawer, drawerApi] = useVbenDrawer({
  onConfirm: onSubmit,
  onOpenChange(isOpen) {
    if (!isOpen) {
      return;
    }

    const data = drawerApi.getData<DrawerData>();
    drawerData.value = data ?? {};
    formApi.resetForm();
    const dict = data?.dict;
    const record = data?.record;
    formApi.setValues({
      typeCode: dict?.typeCode,
      typeName: dict?.typeName,
      sort: 0,
      status: 1,
      ...record,
    });
  },
});

async function getParentOptions() {
  const typeCode = drawerData.value.dict?.typeCode;
  if (!typeCode) {
    return [];
  }

  const list = await getDictDataList({ typeCode }).catch(() => []);
  const tree = toTreeList(Array.isArray(list) ? list : []);
  const editingId = drawerData.value.record?.id;
  if (!editingId) {
    return tree;
  }
  return removeNode(tree, editingId);
}

function toTreeList(list: Record<string, any>[]) {
  const nodeMap = new Map<string, Record<string, any>>();
  const roots: Record<string, any>[] = [];

  for (const item of list) {
    const id = item.id ?? item.dataId;
    if (id === undefined || id === null) {
      continue;
    }
    nodeMap.set(String(id), {
      ...item,
      id,
      dataName: String(item.dataName ?? item.label ?? item.name ?? ''),
      children: [],
    });
  }

  for (const node of nodeMap.values()) {
    const parentId = node.parentId ?? node.pid ?? node.parent ?? node.parentDataId;
    if (parentId === undefined || parentId === null || parentId === '' || Number(parentId) === 0) {
      roots.push(node);
      continue;
    }
    const parent = nodeMap.get(String(parentId));
    if (parent) {
      (parent.children ??= []).push(node);
    } else {
      roots.push(node);
    }
  }

  return roots;
}

function removeNode(
  tree: Record<string, any>[],
  targetId: SystemDictApi.Id,
): Record<string, any>[] {
  return tree
    .filter((node) => String(node.id) !== String(targetId))
    .map((node) => ({
      ...node,
      children: Array.isArray(node.children) ? removeNode(node.children, targetId) : [],
    }));
}

async function onSubmit() {
  const { valid } = await formApi.validate();
  if (!valid) {
    return;
  }

  const selectedDict = drawerData.value.dict;
  const values = await formApi.getValues();
  const formValues = { ...(values as Record<string, any>) };
  delete formValues.typeCode;
  delete formValues.typeName;
  const label = String(formValues.label ?? formValues.dataName ?? '');
  const value = String(formValues.value ?? formValues.dataCode ?? '');
  const payload = {
    ...drawerData.value.record,
    ...formValues,
    dataCode: value,
    dataName: label,
    dictId: selectedDict?.id,
    dictType: selectedDict?.typeCode,
    itemCode: value,
    itemValue: value,
    label,
    parentId: formValues.parentId as SystemDictApi.Id | undefined,
    typeCode: selectedDict?.typeCode,
    typeId: selectedDict?.id,
    typeName: selectedDict?.typeName,
    value,
  };

  drawerApi.lock();
  try {
    if (drawerData.value.record?.id) {
      await updateDictData(payload as SystemDictApi.UpdateDictDataParams);
      ElMessage.success($t('page.common.editSuccess'));
    } else {
      await createDictData(payload as SystemDictApi.CreateDictDataParams);
      ElMessage.success($t('page.common.createSuccess'));
    }
    drawerApi.close();
    emit('success');
  } finally {
    drawerApi.unlock();
  }
}

const getDrawerTitle = computed(() =>
  isEdit.value
    ? $t('page.common.editItem', [$t('system.dict.dataManage')])
    : $t('page.common.createItem', [$t('system.dict.dataManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
