<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { CreateTenantParams, Tenant, UpdateTenantParams } from '#/api/system/tenant/model';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { CommonStatusEnum } from '#/api/core/common';
import { createTenant, updateTenant } from '#/api/system/tenant';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<Tenant>();
const isEdit = computed(() => !!formData.value?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'tenantCode',
    label: $t('system.tenant.code'),
    rules: 'required',
    componentProps: () => ({
      disabled: isEdit.value,
    }),
  },
  {
    component: 'Input',
    fieldName: 'tenantName',
    label: $t('system.tenant.name'),
    rules: 'required',
  },

  {
    component: 'InputNumber',
    fieldName: 'sort',
    label: $t('system.role.sort'),
    defaultValue: 0,
    componentProps: {
      min: 0,
      style: { width: '100%' },
    },
  },
  {
    component: 'RadioGroup',
    fieldName: 'status',
    label: $t('system.role.status'),
    defaultValue: CommonStatusEnum.ENABLED,
    componentProps: {
      options: [
        {
          label: $t('common.enabled'),
          value: CommonStatusEnum.ENABLED,
        },
        {
          label: $t('common.disabled'),
          value: CommonStatusEnum.DISABLED,
        },
      ],
    },
  },
  {
    component: 'Input',
    fieldName: 'remark',
    label: $t('system.role.remark'),
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
      const data = drawerApi.getData<Tenant>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as Tenant;
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
        await updateTenant({
          ...(values as UpdateTenantParams),
          id: formData.value.id,
        });
        ElMessage.success($t('common.actionMessage.editSuccess'));
      } else {
        await createTenant(values as CreateTenantParams);
        ElMessage.success($t('common.actionMessage.createSuccess'));
      }
      drawerApi.close();
      emit('success');
    } catch {
      // 全局错误拦截器会展示接口返回的消息，无需再次提示
    } finally {
      drawerApi.unlock();
    }
  }
}

const getDrawerTitle = computed(() =>
  isEdit.value
    ? $t('common.actionMessage.editItem', [$t('system.tenant.tenantManage')])
    : $t('common.actionMessage.createItem', [$t('system.tenant.tenantManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
