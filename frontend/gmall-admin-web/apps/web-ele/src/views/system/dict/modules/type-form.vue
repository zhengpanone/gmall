<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { createDictType, SystemDictApi, updateDictType } from '#/api/system/dict';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemDictApi.DictType>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'typeCode',
    label: $t('system.dict.typeCode'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'typeName',
    label: $t('system.dict.typeName'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'type',
    label: $t('system.dict.type'),
    rules: 'required',
  },
  {
    component: 'InputNumber',
    fieldName: 'sort',
    label: $t('system.dict.sort'),
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
      const data = drawerApi.getData<SystemDictApi.DictType>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues({
          ...data,
          type: data.type,
        });
      } else {
        formData.value = {} as SystemDictApi.DictType;
        formApi.resetForm();
      }
    }
  },
});

async function onSubmit() {
  const { valid } = await formApi.validate();
  if (valid) {
    drawerApi.lock();
    const values = (await formApi.getValues()) as Record<string, any>;
    const payload = {
      ...formData.value,
      ...values,
      typeCode: values.typeCode,
    };
    try {
      if (formData.value?.id) {
        await updateDictType(payload as SystemDictApi.UpdateDictTypeParams);
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        await createDictType(payload as SystemDictApi.CreateDictTypeParams);
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
