<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { SystemConfigApi, createConfig, updateConfig } from '#/api/system/config';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemConfigApi.Config>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'name',
    label: $t('system.config.name'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'key',
    label: $t('system.config.key'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'value',
    label: $t('system.config.value'),
    rules: 'required',
    componentProps: {
      type: 'textarea',
      rows: 3,
    },
  },
  {
    component: 'InputNumber',
    fieldName: 'type',
    label: $t('system.config.type'),
    defaultValue: 0,
    componentProps: {
      min: 0,
      style: { width: '100%' },
    },
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.config.status'),
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
      const data = drawerApi.getData<SystemConfigApi.Config>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as SystemConfigApi.Config;
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
        await updateConfig(values as SystemConfigApi.UpdateConfigParams);
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        await createConfig(values as SystemConfigApi.CreateConfigParams);
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
    ? $t('page.common.editItem', [$t('system.config.configManage')])
    : $t('page.common.createItem', [$t('system.config.configManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
