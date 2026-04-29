<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import {
  createMenu,
  getMenuOptions,
  SystemMenuApi,
  updateMenu,
} from '#/api/system/menu';

const emit = defineEmits<{
  success: [];
}>();

const formData = ref<SystemMenuApi.Menu>();
const isEdit = computed(() => !!formData.value?.id);

function normalizeParentId(parentId?: number | string) {
  return parentId === 0 || parentId === '0' ? undefined : parentId;
}

const schema: VbenFormSchema[] = [
  {
    component: 'RadioGroup',
    componentProps: {
      buttonStyle: 'solid',
      options: [
        { label: $t('system.menu.type1'), value: 1 },
        { label: $t('system.menu.type2'), value: 2 },
        { label: $t('system.menu.type3'), value: 3 },
      ],
      optionType: 'button',
    },
    defaultValue: 2,
    fieldName: 'type',
    formItemClass: 'col-span-2',
    label: $t('system.menu.type'),
  },
  {
    component: 'ApiTreeSelect',
    componentProps: {
      allowClear: true,
      api: getMenuOptions,
      class: 'w-full',
      childrenField: 'children',
      labelField: 'name',
      valueField: 'id',
      placeholder: $t('system.menu.parentPlaceholder'),
      showSearch: true,
    },
    defaultValue: undefined,
    fieldName: 'parentId',
    label: $t('system.menu.parent'),
  },
  {
    component: 'Input',
    fieldName: 'code',
    label: $t('system.menu.menuCode'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'name',
    label: $t('system.menu.menuName'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'path',
    label: $t('system.menu.path'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'icon',
    label: $t('system.menu.icon'),
  },
  {
    component: 'Input',
    fieldName: 'permission',
    label: $t('system.menu.permission'),
  },
  {
    component: 'Input',
    fieldName: 'component',
    label: $t('system.menu.component'),
  },
  {
    component: 'InputNumber',
    componentProps: {
      min: 0,
      style: { width: '100%' },
    },
    defaultValue: 0,
    fieldName: 'sort',
    label: $t('system.menu.sort'),
  },
  {
    component: 'RadioGroup',
    componentProps: {
      options: [
        { label: $t('common.enabled'), value: 1 },
        { label: $t('common.disabled'), value: 0 },
      ],
    },
    defaultValue: 1,
    fieldName: 'status',
    label: $t('system.menu.status'),
  },
  {
    component: 'Switch',
    fieldName: 'visible',
    label: $t('system.menu.visible'),
  },
  {
    component: 'Switch',
    fieldName: 'keepAlive',
    label: $t('system.menu.keepAlive'),
  },
];

const [Form, formApi] = useVbenForm({
  commonConfig: {
    colon: true,
    formItemClass: 'col-span-1',
  },
  schema,
  showDefaultActions: false,
  wrapperClass: 'grid-cols-2 gap-x-4',
});

const [Drawer, drawerApi] = useVbenDrawer({
  onConfirm: onSubmit,
  onOpenChange(isOpen) {
    if (isOpen) {
      const data = drawerApi.getData<SystemMenuApi.Menu>();
      formApi.resetForm();

      if (data?.id) {
        formData.value = data;
        const formValues = {
          ...data,
          parentId: normalizeParentId(data.parentId),
        };
        formApi.setValues(formValues);
      } else if (data?.parentId !== undefined) {
        formData.value = {} as SystemMenuApi.Menu;
        formApi.setValues({ parentId: normalizeParentId(data.parentId) });
      } else {
        formData.value = {} as SystemMenuApi.Menu;
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
        await updateMenu(formData.value.id, values as SystemMenuApi.UpdateMenuParams);
        ElMessage.success($t('page.common.editSuccess'));
      } else {
        await createMenu(values as SystemMenuApi.CreateMenuParams);
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
    ? $t('page.common.editItem', [$t('system.menu.menuManage')])
    : $t('page.common.createItem', [$t('system.menu.menuManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
