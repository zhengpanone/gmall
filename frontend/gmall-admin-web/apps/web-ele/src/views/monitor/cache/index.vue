<script setup lang="ts">
import type { CommandStats, RedisInfo } from '#/api/monitor/cache/model';

import { computed, onMounted, reactive, ref } from 'vue';

import { Page } from '@vben/common-ui';
import { CommandLineIcon, MemoryIcon, RedisIcon } from '@vben/icons';

import {
  ElButton,
  ElCard,
  ElCol,
  ElEmpty,
  ElRow,
  ElSkeleton,
} from 'element-plus';

import { redisCacheInfo } from '#/api/monitor/cache/index';

import CommandChart from './components/command-chart.vue';
import MemoryChart from './components/memory-chart.vue';
import RedisDescription from './components/redis-description.vue';

const baseSpan = { lg: 12, md: 24, sm: 24, xl: 12, xs: 24 };

const chartData = reactive<{
  command: { name: string; value: number }[];
  memory: string;
}>({
  command: [],
  memory: '0',
});

interface IRedisInfo extends RedisInfo {
  dbSize?: string;
}
const redisInfo = ref<IRedisInfo>();
const loading = ref(false);

const hasCommandData = computed(() => chartData.command.length > 0);
const hasMemoryData = computed(() => Number.parseFloat(chartData.memory) > 0);

onMounted(async () => {
  await loadInfo();
});

async function loadInfo() {
  loading.value = true;
  try {
    const result = await redisCacheInfo();
    const ret = result.data;

    const usedMemory = formatMemoryByMb(ret.info.used_memory);
    chartData.memory = usedMemory;

    chartData.command = formatCommandStats(ret.commandStats ?? []);
    redisInfo.value = { ...ret.info, dbSize: String(ret.dbSize) };
  } catch (error) {
    console.warn(error);
  } finally {
    loading.value = false;
  }
}

function formatCommandStats(data: CommandStats[]) {
  return data
    .map((item) => ({
      name: item.command,
      value: item.calls,
    }))
    .filter((item) => item.name && item.value > 0)
    .sort((prev, next) => next.value - prev.value);
}

function formatMemoryByMb(value?: string) {
  const bytes = Number.parseInt(value ?? '0', 10);
  if (!Number.isFinite(bytes) || bytes <= 0) {
    return '0';
  }

  return (bytes / 1024 / 1024).toFixed(2);
}
</script>

<template>
  <Page>
    <ElRow :gutter="15">
      <ElCol :span="24">
        <ElCard v-loading="loading" class="mb-[15px]" shadow="never">
          <template #header>
            <div class="flex items-center justify-between">
              <div class="flex items-center justify-start gap-[6px]">
                <RedisIcon class="size-[16px]" />
                <span>redis信息</span>
              </div>
              <ElButton :loading="loading" size="small" @click="loadInfo">
                <span class="icon-[charm--refresh]"></span>
              </ElButton>
            </div>
          </template>
          <RedisDescription v-if="redisInfo" :data="redisInfo" />
          <ElSkeleton v-else :rows="3" animated />
        </ElCard>
      </ElCol>

      <ElCol v-bind="baseSpan" class="mb-[15px]">
        <ElCard v-loading="loading" shadow="never">
          <template #header>
            <div class="flex items-center gap-[6px]">
              <CommandLineIcon class="size-[16px]" />
              <span>命令统计</span>
            </div>
          </template>
          <CommandChart v-if="hasCommandData" :data="chartData.command" />
          <ElEmpty v-else :image-size="80" description="暂无命令统计" />
        </ElCard>
      </ElCol>

      <ElCol v-bind="baseSpan">
        <ElCard v-loading="loading" shadow="never">
          <template #header>
            <div class="flex items-center justify-start gap-[6px]">
              <MemoryIcon class="size-[16px]" />
              <span>内存占用</span>
            </div>
          </template>
          <MemoryChart v-if="hasMemoryData" :data="chartData.memory" />
          <ElEmpty v-else :image-size="80" description="暂无内存数据" />
        </ElCard>
      </ElCol>
    </ElRow>
  </Page>
</template>
