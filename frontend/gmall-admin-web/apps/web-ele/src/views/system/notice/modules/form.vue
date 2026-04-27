<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import {
  SystemNoticeApi,
  createNotice,
  updateNotice,
} from '#/api/system/notice';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemNoticeApi.Notice>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'title',
    label: $t('system.notice.title'),
    rules: 'required',
  },
  {
    component: 'RadioGroup',
    fieldName: 'type',
    label: $t('system.notice.type'),
    defaultValue: 1,
    componentProps: {
      options: [
        { label: $t('system.notice.type1'), value: 1 },
        { label: $t('system.notice.type2'), value: 2 },
      ],
    },
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.notice.status'),
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
    fieldName: 'content',
    label: $t('system.notice.content'),
    rules: 'required',
    formItemClass: 'col-span-1',
    componentProps: {
      type: 'textarea',
      rows: 6,
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
      const data = drawerApi.getData<SystemNoticeApi.Notice>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as SystemNoticeApi.Notice;
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
        await updateNotice(values as SystemNoticeApi.UpdateNoticeParams);
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        await createNotice(values as SystemNoticeApi.CreateNoticeParams);
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
    ? $t('page.common.editItem', [$t('system.notice.noticeManage')])
    : $t('page.common.createItem', [$t('system.notice.noticeManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
