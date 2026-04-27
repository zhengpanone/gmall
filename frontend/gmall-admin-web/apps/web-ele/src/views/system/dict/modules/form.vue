<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { SystemDictApi, createDict, updateDict } from '#/api/system/dict';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemDictApi.Dict>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'name',
    label: $t('system.dict.name'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'code',
    label: $t('system.dict.code'),
    rules: 'required',
  },
  {
    component: 'InputNumber',
    fieldName: 'type',
    label: $t('system.dict.type'),
    defaultValue: 0,
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
      type: 'textarea',
      rows: 3,
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
      const data = drawerApi.getData<SystemDictApi.Dict>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as SystemDictApi.Dict;
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
        await updateDict(values as SystemDictApi.UpdateDictParams);
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        await createDict(values as SystemDictApi.CreateDictParams);
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
    ? $t('page.common.editItem', [$t('system.dict.dictManage')])
    : $t('page.common.createItem', [$t('system.dict.dictManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
