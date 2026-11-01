<script lang="ts" setup>
import type { VbenFormSchema } from '@vben/common-ui';
import type { BasicOption } from '@vben/types';

import { computed, nextTick, onMounted, ref } from 'vue';

import { AuthenticationLogin, z } from '@vben/common-ui';
import { $t } from '@vben/locales';

import {
  checkCaptcha,
  type ConfigResult,
  getAnonymousConfigListApi,
  getCaptcha,
} from '#/api/core/common';
import { getTenantByWebsite } from '#/api/system/tenant';
import { useAuthStore } from '#/store';

defineOptions({ name: 'Login' });

const authStore = useAuthStore();
const captchaEnable = ref(false);

const loginRef = ref();
const verifyRef = ref();

const captchaType = 'blockPuzzle'; // 验证码类型：'blockPuzzle' | 'clickWord'

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

async function loadConfig() {
  try {
    const configResult = await getAnonymousConfigListApi(['CAPTCHA_ENABLE']);
    const configMap = configResult as unknown as Record<string, ConfigResult>;

    captchaEnable.value = configMap?.CAPTCHA_ENABLE?.configValue === 'true';
  } catch (error) {
    console.error('Failed to load captcha config:', error);
    captchaEnable.value = false;
  }
}

onMounted(() => {
  void loadConfig();
  void loadTenantOptions();
});

async function handleLogin(values: any) {
  // 如果开启验证码，则先验证验证码
  if (captchaEnable.value) {
    verifyRef.value.show();
    return;
  }
  // 无验证码直接登录
  await authStore.authLogin('username', values);
}

/** 验证码通过，执行登录 */
async function handleVerifySuccess({ captchaVerification }: any) {
  try {
    await authStore.authLogin('username', {
      ...(await loginRef.value.getFormApi().getValues()),
      captchaVerification,
    });
  } catch (error) {
    console.error('Error in handleLogin:', error);
  }
}

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
  ];
});
</script>

<template>
  <div>
    <AuthenticationLogin
      ref="loginRef"
      :form-schema="formSchema"
      :loading="authStore.loginLoading"
      @submit="handleLogin"
    />
    <Verification
      ref="verifyRef"
      v-if="captchaEnable"
      :captcha-type="captchaType"
      :check-captcha-api="checkCaptcha"
      :get-captcha-api="getCaptcha"
      :img-size="{ width: '400px', height: '200px' }"
      mode="pop"
      @on-success="handleVerifySuccess"
    />
  </div>
</template>
