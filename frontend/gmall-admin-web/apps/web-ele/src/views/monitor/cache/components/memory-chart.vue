<script setup lang="ts">
import type { EchartsUIType } from '@vben/plugins/echarts';

import { onActivated, onMounted, ref, watch } from 'vue';

import { EchartsUI, useEcharts } from '@vben/plugins/echarts';

interface Props {
  data?: string;
}

const props = withDefaults(defineProps<Props>(), {
  data: '0',
});

const memoryHtmlRef = ref<EchartsUIType>();
const { renderEcharts, resize } = useEcharts(memoryHtmlRef);

watch(
  () => props.data,
  () => {
    if (!memoryHtmlRef.value) return;
    setEchartsOption(props.data);
  },
  { immediate: true },
);

onMounted(() => {
  setEchartsOption(props.data);
});
// 从其他页面切换回来会有一个奇怪的动画效果 需要调用resize
onActivated(resize);

/**
 * 获取最近的十的幂次
 * 该函数用于寻找大于给定数字num的最近的10的幂次
 * 主要解决的问题是确定一个数附近较大的十的幂次，这在某些算法中很有用
 *
 * @param num {number} 输入的数字，用于寻找最近的十的幂次
 */
function getNearestPowerOfTen(num: number) {
  let power = 10;
  while (power <= num) {
    power *= 10;
  }
  return power;
}

type EChartsOption = Parameters<typeof renderEcharts>['0'];
function setEchartsOption(value: string) {
  const parsedValue = Number.parseFloat(value);
  const formattedValue = Math.floor(parsedValue);
  const max = getNearestPowerOfTen(formattedValue);
  const options: EChartsOption = {
    series: [
      {
        axisLabel: {
          color: '#8f99ad',
          distance: -28,
          fontSize: 14,
        },
        axisLine: {
          lineStyle: {
            color: [
              [Math.min(parsedValue / max, 1), '#409eff'],
              [1, '#434a54'],
            ],
            width: 12,
          },
        },
        axisTick: {
          distance: -18,
          lineStyle: {
            color: '#626a7a',
            width: 2,
          },
          length: 8,
          splitNumber: 5,
        },
        animation: true,
        animationDuration: 1000,
        data: [
          {
            name: '内存消耗',
            value: parsedValue,
          },
        ],
        detail: {
          color: 'inherit',
          fontSize: 32,
          fontWeight: 700,
          formatter: `${value}M`,
          offsetCenter: [0, '45%'],
          valueAnimation: true,
        },
        max,
        min: 0,
        name: '峰值',
        pointer: {
          icon: 'path://M2,0 L120,8 L120,-8 Z',
          itemStyle: {
            color: '#409eff',
          },
          length: '65%',
          width: 8,
        },
        progress: {
          itemStyle: {
            color: '#409eff',
          },
          roundCap: false,
          show: true,
          width: 12,
        },
        splitLine: {
          distance: -24,
          length: 16,
          lineStyle: {
            color: '#626a7a',
            width: 3,
          },
        },
        title: {
          color: '#8f99ad',
          fontSize: 16,
          offsetCenter: [0, '25%'],
        },
        radius: '78%',
        type: 'gauge',
      },
    ],
    tooltip: {
      formatter: `{b} <br/>{a} : ${value}M`,
    },
  };
  renderEcharts(options);
}
</script>

<template>
  <EchartsUI ref="memoryHtmlRef" height="400px" width="100%" />
</template>
