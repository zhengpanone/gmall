import type { RouteRecordRaw } from 'vue-router';

import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    meta: {
      icon: 'ic:baseline-view-in-ar',
      keepAlive: true,
      order: 1000,
      title: $t('monitor.title'),
    },
    name: 'Monitor',
    path: '/monitor',
    children: [
      {
        meta: {
          title: $t('monitor.cache.title'),
        },
        name: 'Cache',
        path: '/monitor/cache',
        component: () => import('#/views/monitor/cache/index.vue'),
      },
      {
        meta: {
          title: $t('monitor.online.title'),
        },
        name: 'Online',
        path: '/monitor/online',
        component: () => import('#/views/monitor/online/index.vue'),
      },
      {
        meta: {
          title: $t('monitor.admin.title'),
        },
        name: 'AminMonitor',
        path: '/monitor/admin',
        component: () => import('#/views/monitor/admin/index.vue'),
      },
      {
        meta: {
          title: $t('monitor.task.title'),
        },
        name: 'Task',
        path: '/monitor/task',
        component: () => import('#/views/monitor/task/index.vue'),
      },
    ],
  },
];

export default routes;
