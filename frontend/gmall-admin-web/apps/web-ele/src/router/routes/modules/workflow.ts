import type { RouteRecordRaw } from 'vue-router';

import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    meta: {
      icon: 'ic:baseline-view-in-ar',
      keepAlive: true,
      order: 1000,
      title: $t('workflow.title'),
    },
    name: 'Workflow',
    path: '/workflow',
    children: [
      {
        meta: {
          title: $t('workflow.category.title'),
        },
        name: 'Category',
        path: '/workflow/category',
        component: () => import('#/views/workflow/category/index.vue'),
      },
      {
        meta: {
          icon: 'ic:baseline-view-in-ar',
          keepAlive: true,
          order: 1000,
          title: $t('workflow.monitor.title'),
        },
        name: 'WorkflowMonitor',
        path: '/workflow/monitor',
        children: [
          {
            meta: {
              title: $t('workflow.monitor.allTaskWait.title'),
            },
            name: 'AllTaskWaiting',
            path: '/workflow/monitor/allTaskWaiting',
            component: () => import('#/views/workflow/monitor/allTaskWaiting/index.vue'),
          },
          {
            meta: {
              title: $t('workflow.monitor.processInstance.title'),
            },
            name: 'ProcessInstance',
            path: '/workflow/monitor/processInstance',
            component: () => import('#/views/workflow/monitor/processInstance/index.vue'),
          },
        ],
      },
      {
        meta: {
          title: $t('workflow.processDefinition.title'),
        },
        name: 'ProcessDefinition',
        path: '/workflow/processDefinition',
        component: () => import('#/views/workflow/processDefinition/index.vue'),
      },
      {
        meta: {
          title: $t('workflow.processDesign.title'),
        },
        name: 'ProcessDesign',
        path: '/workflow/processDesign',
        component: () => import('#/views/workflow/processDesign/index.vue'),
      },
      {
        meta: {
          title: $t('workflow.spel.title'),
        },
        name: 'Spel',
        path: '/workflow/spel',
        component: () => import('#/views/workflow/spel/index.vue'),
      },
    ],
  },
];

export default routes;
