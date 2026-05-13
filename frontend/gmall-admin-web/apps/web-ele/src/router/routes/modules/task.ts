import type { RouteRecordRaw } from 'vue-router';

import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    meta: {
      icon: 'ic:baseline-view-in-ar',
      keepAlive: true,
      order: 1000,
      title: $t('task.title'),
    },
    name: 'Task',
    path: '/task',
    children: [
      {
        meta: {
          title: $t('task.myTask.title'),
        },
        name: 'Cache',
        path: '/task/myTask',
        component: () => import('#/views/task/myTask/index.vue'),
      },
      {
        meta: {
          title: $t('task.taskTodo.title'),
        },
        name: 'TaskTodo',
        path: '/task/taskTodo',
        component: () => import('#/views/task/taskTodo/index.vue'),
      },
      {
        meta: {
          title: $t('task.taskFinish.title'),
        },
        name: 'TaskFinish',
        path: '/task/taskFinish',
        component: () => import('#/views/task/taskFinish/index.vue'),
      },
      {
        meta: {
          title: $t('task.taskCopy.title'),
        },
        name: 'TaskCopy',
        path: '/task/taskCopy',
        component: () => import('#/views/task/taskCopy/index.vue'),
      },
    ],
  },
];

export default routes;
