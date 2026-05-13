import type { RouteRecordRaw } from 'vue-router';

import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    meta: {
      icon: 'ic:baseline-view-in-ar',
      keepAlive: true,
      order: 1000,
      title: $t('tool.title'),
    },
    name: 'Tool',
    path: '/tool',
    children: [
      {
        meta: {
          title: $t('tool.gen.title'),
        },
        name: 'CodeGen',
        path: '/tool/codegen',
        component: () => import('#/views/tool/codegen/index.vue'),
      },
    ],
  },
];

export default routes;
