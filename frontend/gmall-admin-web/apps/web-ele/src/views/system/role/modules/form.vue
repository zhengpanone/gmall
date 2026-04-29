<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { createRole, SystemRoleApi, updateRole } from '#/api/system/role';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemRoleApi.Role>();
const isEdit = computed(() => !!formData.value?.id);

const roleTypeOptions = [
  { label: $t('system.role.type1'), value: SystemRoleApi.RoleTypeEnum.SYSTEM },
  { label: $t('system.role.type2'), value: SystemRoleApi.RoleTypeEnum.CUSTOM },
];

function getBizErrorMessage(response: any) {
  if (!response || typeof response !== 'object') return '';
  return response.msg || response.message || response.error || '';
}

function isBizSuccess(response: any) {
  // 兼容后端只返回 {} 的场景：无 code 字段时按成功处理
  if (!response || typeof response !== 'object' || !('code' in response)) {
    return true;
  }
  const code = Number((response as any).code);
  return code === 0 || code === 200;
}

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'roleCode',
    label: $t('system.role.code'),
    rules: 'required',
    componentProps: () => ({
      disabled: isEdit.value,
    }),
  },
  {
    component: 'Input',
    fieldName: 'roleName',
    label: $t('system.role.name'),
    rules: 'required',
  },
  {
    component: 'RadioGroup',
    fieldName: 'roleType',
    label: $t('system.role.type'),
    rules: 'required',
    componentProps: {
      options: roleTypeOptions,
    },
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
    defaultValue: SystemRoleApi.RoleStatusEnum.ENABLED,
    componentProps: {
      options: [
        {
          label: $t('common.enabled'),
          value: SystemRoleApi.RoleStatusEnum.ENABLED,
        },
        {
          label: $t('common.disabled'),
          value: SystemRoleApi.RoleStatusEnum.DISABLED,
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
      const data = drawerApi.getData<SystemRoleApi.Role>();
      if (data?.id) {
        formData.value = data;
        formApi.setValues(data);
      } else {
        formData.value = {} as SystemRoleApi.Role;
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
        const resp = await updateRole({
          ...(values as SystemRoleApi.UpdateRoleParams),
          id: formData.value.id,
        });
        if (!isBizSuccess(resp)) {
          ElMessage.error(
            getBizErrorMessage(resp) || $t('ui.actionMessage.operationFailed'),
          );
          return;
        }
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        const resp = await createRole(values as SystemRoleApi.CreateRoleParams);
        if (!isBizSuccess(resp)) {
          ElMessage.error(
            getBizErrorMessage(resp) || $t('ui.actionMessage.operationFailed'),
          );
          return;
        }
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
    ? $t('page.common.editItem', [$t('system.role.roleManage')])
    : $t('page.common.createItem', [$t('system.role.roleManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
