<script setup lang="ts">
import type { RedisInfo } from '#/api/monitor/cache/model';

import { computed } from 'vue';

import { useWindowSize } from '@vueuse/core';

import { ElDescriptions, ElDescriptionsItem } from 'element-plus';

interface IRedisInfo extends RedisInfo {
  dbSize?: string;
}

defineProps<{ data: IRedisInfo }>();

const { width } = useWindowSize();
const column = computed(() => {
  if (width.value >= 1200) {
    return 4;
  }
  if (width.value >= 768) {
    return 2;
  }
  return 1;
});
</script>

<template>
  <ElDescriptions bordered :column="column" size="small">
    <ElDescriptionsItem label="redis版本">
      {{ data.redis_version }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="redis模式">
      {{ data.redis_mode === 'standalone' ? '单机模式' : '集群模式' }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="tcp端口">
      {{ data.tcp_port }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="客户端数">
      {{ data.connected_clients }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="运行时间"> {{ data.uptime_in_days }} 天 </ElDescriptionsItem>
    <ElDescriptionsItem label="使用内存">
      {{ data.used_memory_human }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="使用CPU">
      {{ Number.parseFloat(data?.used_cpu_user_children ?? '0').toFixed(2) }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="内存配置">
      {{ data.maxmemory_human }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="AOF是否开启">
      {{ data.aof_enabled === '0' ? '否' : '是' }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="RDB是否成功">
      {{ data.rdb_last_bgsave_status }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="key数量">
      {{ data.dbSize }}
    </ElDescriptionsItem>
    <ElDescriptionsItem label="网络入口/出口">
      {{
        `${data.instantaneous_input_kbps ?? '0.00'}kps/${
          data.instantaneous_output_kbps ?? '0.00'
        }kps`
      }}
    </ElDescriptionsItem>
  </ElDescriptions>
</template>
