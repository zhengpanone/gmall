import { defineConfig } from '@vben/vite-config';

import ElementPlus from 'unplugin-element-plus/vite';

export default defineConfig(async () => {
  return {
    application: {},
    vite: {
      plugins: [
        ElementPlus({
          format: 'esm',
        }),
      ],
      server: {
        proxy: {
          '/api': {
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/api/, ''),
            // mock代理目标地址
            target: 'http://localhost:5320/api',
            ws: true,
          },
          '/backend': {
            changeOrigin: true,
            rewrite: (path) => path.replace(/^\/backend/, ''),
            // 后端服务代理地址
            target: 'http://localhost:48080',
            ws: true,
          },
        },
      },
    },
  };
});
