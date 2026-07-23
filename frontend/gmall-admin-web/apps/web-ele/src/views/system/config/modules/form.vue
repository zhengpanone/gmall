<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { SystemConfigApi } from '#/api/system/config';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { CommonStatusEnum } from '#/api/core/common';
import { createConfig, updateConfig } from '#/api/system/config';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemConfigApi.Config>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'category',
    label: $t('system.config.category'),
    rules: 'required',
    componentProps: {
      clearable: true,
      maxlength: 50,
      placeholder: $t('system.config.category'),
      showWordLimit: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'configName',
    label: $t('system.config.name'),
    rules: 'required',
    componentProps: {
      clearable: true,
      maxlength: 50,
      placeholder: $t('system.config.name'),
      showWordLimit: true,
    },
  },
  {
    component: 'Input',
    fieldName: 'configKey',
    label: $t('system.config.key'),
    rules: 'required',
    componentProps: () => ({
      clearable: true,
      disabled: isEdit.value,
      maxlength: 50,
      placeholder: 'system_name',
      showWordLimit: true,
    }),
  },
  {
    component: 'Input',
    fieldName: 'configValue',
    label: $t('system.config.value'),
    rules: 'required',
    componentProps: {
      maxlength: 500,
      placeholder: $t('system.config.value'),
      rows: 4,
      showWordLimit: true,
      type: 'textarea',
    },
  },
  {
    component: 'Input',
    fieldName: 'configType',
    label: $t('system.config.type'),
    rules: 'required',
    componentProps: {
      clearable: true,
      maxlength: 50,
      placeholder: 'system',
      showWordLimit: true,
    },
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.config.status'),
    defaultValue: CommonStatusEnum.ENABLED,
    componentProps: {
      options: [
        { label: $t('common.enabled'), value: CommonStatusEnum.ENABLED },
        { label: $t('common.disabled'), value: CommonStatusEnum.DISABLED },
      ],
    },
  },
  {
    component: 'Input',
    fieldName: 'remark',
    label: $t('system.config.remark'),
    componentProps: {
      maxlength: 500,
      placeholder: $t('system.config.remark'),
      rows: 3,
      showWordLimit: true,
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
  if (!valid) {
    return;
  }

  drawerApi.lock();
  const values = await formApi.getValues();
  try {
    if (formData.value?.id) {
      await updateConfig({
        ...(values as SystemConfigApi.CreateConfigParams),
        id: formData.value.id,
      });
      ElMessage.success($t('common.actionMessage.editSuccess'));
    } else {
      await createConfig(values as SystemConfigApi.CreateConfigParams);
      ElMessage.success($t('common.actionMessage.createSuccess'));
    }
    drawerApi.close();
    emit('success');
  } finally {
    drawerApi.unlock();
  }
}

const getDrawerTitle = computed(() =>
  isEdit.value
    ? $t('common.actionMessage.editItem', [$t('system.config.configManage')])
    : $t('common.actionMessage.createItem', [$t('system.config.configManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
