<script lang="ts" setup>
import type { VbenFormSchema } from '#/adapter/form';
import type { SystemDictApi } from '#/api/system/dict';

import { computed, ref } from 'vue';

import { useVbenDrawer } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { ElMessage } from 'element-plus';

import { useVbenForm } from '#/adapter/form';
import { createDictData, updateDictData } from '#/api/system/dict';

const emit = defineEmits<{
  success: [];
}>();

interface DrawerData {
  dict?: SystemDictApi.Dict;
  record?: Partial<SystemDictApi.DictData>;
}

const drawerData = ref<DrawerData>({});
const isEdit = computed(() => !!drawerData.value.record?.id);

const schema: VbenFormSchema[] = [
  {
    component: 'Input',
    fieldName: 'label',
    label: $t('system.dict.dataLabel'),
    rules: 'required',
  },
  {
    component: 'Input',
    fieldName: 'value',
    label: $t('system.dict.dataValue'),
    rules: 'required',
  },
  {
    component: 'InputNumber',
    fieldName: 'sort',
    label: $t('system.dict.dataSort'),
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
      rows: 3,
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
    if (!isOpen) {
      return;
    }

    const data = drawerApi.getData<DrawerData>();
    drawerData.value = data ?? {};
    formApi.resetForm();
    formApi.setValues({
      sort: 0,
      status: 1,
      ...(data?.record ?? {}),
    });
  },
});

function getDictType(dict?: SystemDictApi.Dict) {
  return String(dict?.type ?? dict?.code ?? dict?.dictType ?? '');
}

async function onSubmit() {
  const { valid } = await formApi.validate();
  if (!valid) {
    return;
  }

  const selectedDict = drawerData.value.dict;
  const values = await formApi.getValues();
  const payload = {
    ...drawerData.value.record,
    ...values,
    dictId: selectedDict?.id,
    dictType: getDictType(selectedDict),
    itemCode: values.value,
    itemValue: values.value,
  };

  drawerApi.lock();
  try {
    if (drawerData.value.record?.id) {
      await updateDictData(payload as SystemDictApi.UpdateDictDataParams);
      ElMessage.success($t('page.common.editSuccess'));
    } else {
      await createDictData(payload as SystemDictApi.CreateDictDataParams);
      ElMessage.success($t('page.common.createSuccess'));
    }
    drawerApi.close();
    emit('success');
  } finally {
    drawerApi.unlock();
  }
}

const getDrawerTitle = computed(() =>
  isEdit.value
    ? $t('page.common.editItem', [$t('system.dict.dataManage')])
    : $t('page.common.createItem', [$t('system.dict.dataManage')]),
);
</script>

<template>
  <Drawer class="w-full max-w-150" :title="getDrawerTitle">
    <Form class="mx-4" layout="horizontal" />
  </Drawer>
</template>
