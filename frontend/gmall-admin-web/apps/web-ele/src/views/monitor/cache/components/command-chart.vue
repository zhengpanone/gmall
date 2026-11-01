<script setup lang="ts">
import type { EchartsUIType } from '@vben/plugins/echarts';

import { onActivated, onMounted, ref, watch } from 'vue';

import { EchartsUI, useEcharts } from '@vben/plugins/echarts';

interface Props {
  data?: { name: string; value: number }[];
}

const props = withDefaults(defineProps<Props>(), {
  data: () => [],
});

const chartRef = ref<EchartsUIType>();
const { renderEcharts, resize } = useEcharts(chartRef);

watch(
  () => props.data,
  () => {
    if (!chartRef.value) return;
    setEchartsOption(props.data);
  },
  { immediate: true },
);

onMounted(() => {
  setEchartsOption(props.data);
});
/**
 * 从其他页面切换回来会有一个奇怪的动画效果 需要调用resize
 * 该饼图组件需要关闭animation
 */
onActivated(() => resize());

type EChartsOption = Parameters<typeof renderEcharts>['0'];
function setEchartsOption(data: Props['data']) {
  const option: EChartsOption = {
    color: [
      '#5470c6',
      '#91cc75',
      '#fac858',
      '#ee6666',
      '#73c0de',
      '#3ba272',
      '#fc8452',
      '#9a60b4',
      '#ea7ccc',
    ],
    series: [
      {
        animationDuration: 1000,
        animationEasing: 'cubicInOut',
        center: ['50%', '50%'],
        data,
        label: {
          formatter: '{b}',
        },
        labelLine: {
          length: 16,
          length2: 12,
          smooth: true,
        },
        name: '命令',
        radius: ['7%', '48%'],
        roseType: 'radius',
        type: 'pie',
      },
    ],
    tooltip: {
      formatter: '{a}<br />{b}: {c} 次 ({d}%)',
      trigger: 'item',
    },
  };
  renderEcharts(option);
}
</script>

<template>
  <EchartsUI ref="chartRef" height="420px" width="100%" />
</template>
