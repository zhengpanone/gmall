<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { SystemDeptApi, createDept, getDeptOptions, updateDept } from '#/api/system/dept';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemDeptApi.Dept>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'TreeSelect',
    fieldName: 'parentId',
    label: $t('system.dept.parent'),
    componentProps: {
      api: getDeptOptions,
      dataFields: {
        label: 'name',
        value: 'id',
        children: 'children',
      },
      placeholder: $t('system.dept.parentPlaceholder'),
      allowClear: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'name',
    label: $t('system.dept.name'),
    rules: 'required',
  },
  {
    component: 'InputNumber',
    fieldName: 'sort',
    label: $t('system.dept.sort'),
    defaultValue: 0,
    componentProps: {
      min: 0,
      style: { width: '100%' },
    },
  },
  {
    component: 'Input',
    fieldName: 'leader',
    label: $t('system.dept.leader'),
  },
  {
    component: 'Input',
    fieldName: 'phone',
    label: $t('system.dept.phone'),
  },
  {
    component: 'Input',
    fieldName: 'email',
    label: $t('system.dept.email'),
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.dept.status'),
    defaultValue: 1,
    componentProps: {
      options: [
        { label: $t('common.enabled'), value: 1 },
        { label: $t('common.disabled'), value: 0 },
      ],
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
    if (isOpen) {
      const data = drawerApi.getData<SystemDeptApi.Dept>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as SystemDeptApi.Dept;
        formApi.resetForm();
      }
    }
  },
});

async function onSubmit() {
  const { valid } = await formApi.validate();
  if (valid) {
    drawerApi.lock();
    const values = await formApi.getValues();
    try {
      if (formData.value?.id) {
        await updateDept(values as SystemDeptApi.UpdateDeptParams);
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        await createDept(values as SystemDeptApi.CreateDeptParams);
        ElMessage.success($t('page.common.createSuccess'));
      }
      drawerApi.close();
      emit('success');
    } finally {
      drawerApi.unlock();
    }
  }
}

const getDrawerTitle = computed(() =>
  isEdit.value
    ? $t('page.common.editItem', [$t('system.dept.deptManage')])
    : $t('page.common.createItem', [$t('system.dept.deptManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
