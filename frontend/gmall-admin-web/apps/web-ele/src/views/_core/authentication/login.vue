<script lang="ts" setup>
import type { VbenFormSchema } from '@vben/common-ui';
import type { BasicOption } from '@vben/types';

import { computed, markRaw, nextTick, onMounted, ref } from 'vue';

import { AuthenticationLogin, SliderCaptcha, z } from '@vben/common-ui';
import { $t } from '@vben/locales';

import { getTenantByWebsite } from '#/api/system/tenant';
import { useAuthStore } from '#/store';

defineOptions({ name: 'Login' });

const authStore = useAuthStore();
const loginRef = ref();

const tenantOptions = ref<BasicOption[]>([]);
const tenantOptionsLoading = ref(false);

async function loadTenantOptions() {
  tenantOptionsLoading.value = true;
  try {
    const website = window.location.host;
    const result = await getTenantByWebsite(website);
    if (!result) {
      tenantOptions.value = [];
      return;
    }

    tenantOptions.value = [
      {
        label: result.tenantName,
        value: result.id,
      },
    ];

    await nextTick();
    loginRef.value?.getFormApi()?.setFieldValue('tenantId', result.id);
  } finally {
    tenantOptionsLoading.value = false;
  }
}

onMounted(loadTenantOptions);

const formSchema = computed((): VbenFormSchema[] => {
  return [
    {
      component: 'VbenSelect',
      componentProps: {
        loading: tenantOptionsLoading.value,
        options: tenantOptions.value,
        placeholder: $t('authentication.selectAccount'),
      },
      defaultValue: tenantOptions.value[0]?.value,
      fieldName: 'tenantId',
      label: $t('authentication.selectAccount'),
      rules: z.string().min(1, { message: $t('authentication.selectAccount') }),
    },
    {
      component: 'VbenInput',
      componentProps: {
        placeholder: $t('authentication.usernameTip'),
      },
      dependencies: {
        trigger(values, form) {
          if (!values.tenantId) return;

          form.setValues({
            password: 'admin123',
          });
        },
        triggerFields: ['tenantId'],
      },
      fieldName: 'username',
      label: $t('authentication.username'),
      rules: z.string().min(1, { message: $t('authentication.usernameTip') }),
    },
    {
      component: 'VbenInputPassword',
      componentProps: {
        placeholder: $t('authentication.password'),
      },
      fieldName: 'password',
      label: $t('authentication.password'),
      rules: z.string().min(1, { message: $t('authentication.passwordTip') }),
    },
    {
      component: markRaw(SliderCaptcha),
      fieldName: 'captcha',
      rules: z.boolean().refine((value) => value, {
        message: $t('authentication.verifyRequiredTip'),
      }),
    },
  ];
});
</script>

<template>
  <AuthenticationLogin
    ref="loginRef"
    :form-schema="formSchema"
    :loading="authStore.loginLoading"
    @submit="authStore.authLogin"
  />
</template>
