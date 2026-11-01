<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { SystemUserApi } from '#/api/system/user';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { CommonStatusEnum } from '#/api/core/common';
import { createUser, updateUser } from '#/api/system/user';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemUserApi.User>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'username',
    label: $t('system.user.username'),
    rules: 'required',
    componentProps: {
      disabled: isEdit.value,
    },
  },
  {
    component: 'Input',
    fieldName: 'nickname',
    label: $t('system.user.nickname'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'email',
    label: $t('system.user.email'),
  },
  {
    component: 'Input',
    fieldName: 'mobile',
    label: $t('system.user.mobile'),
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.user.status'),
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
    label: $t('system.user.remark'),
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
      const data = drawerApi.getData<SystemUserApi.User>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as SystemUserApi.User;
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
        await updateUser({
          ...(values as SystemUserApi.UpdateUserParams),
          id: formData.value.id,
        });
        ElMessage.success($t('common.actionMessage.editSuccess'));
      } else {
        await createUser(values as SystemUserApi.CreateUserParams);
        ElMessage.success($t('common.actionMessage.createSuccess'));
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
    ? $t('common.actionMessage.editItem', [$t('system.user.userManage')])
    : $t('common.actionMessage.createItem', [$t('system.user.userManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
