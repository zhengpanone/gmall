import type { RouteRecordRaw } from 'vue-router';

import { $t } from '#/locales';

const routes: RouteRecordRaw[] = [
  {
    meta: {
      icon: 'ic:baseline-view-in-ar',
      keepAlive: true,
      order: 1000,
      title: $t('system.title'),
    },
    name: 'System Management',
    path: '/',
    children: [
      {
        meta: {
          icon: 'ic:baseline-view-in-ar',
          keepAlive: true,
          order: 1001,
          title: $t('system.system.systemSetting'),
        },
        name: 'System Setting',
        path: '/',
        children: [
             {
            meta: {
              title: $t('system.menu.menuManage'),
            },
            name: 'MenuManage',
            path: '/system/menu-manage',
            component: () => import('#/views/system/menu/index.vue'),
          }
        ]
      
      },
    
   
      {
        meta: {
          title: $t('system.role.roleManage'),
        },
        name: 'RoleManage',
        path: '/system/role-manage',
        component: () => import('#/views/system/role/index.vue'),
      },
      {
        meta: {
          title: $t('system.dept.deptManage'),
        },
        name: 'DeptManage',
        path: '/system/dept-manage',
        component: () => import('#/views/system/dept/index.vue'),
      },
      {
        meta: {
          title: $t('system.user.userManage'),
        },
        name: 'UserManage',
        path: '/system/user-manage',
        component: () => import('#/views/system/user/index.vue'),
      },
      {
        meta: {
          title: $t('system.dict.dictManage'),
        },
        name: 'DictManage',
        path: '/system/dict-manage',
        component: () => import('#/views/system/dict/index.vue'),
      },
      {
        meta: {
          title: $t('system.config.configManage'),
        },
        name: 'ConfigManage',
        path: '/system/config-manage',
        component: () => import('#/views/system/config/index.vue'),
      },
      {
        meta: {
          title: $t('system.notice.noticeManage'),
        },
        name: 'NoticeManage',
        path: '/system/notice-manage',
        component: () => import('#/views/system/notice/index.vue'),
      },
      {
        meta: {
          title: $t('system.log.operLog'),
        },
        name: 'OperLog',
        path: '/system/oper-log',
        component: () => import('#/views/system/log/oper/index.vue'),
      },
      {
        meta: {
          title: $t('system.log.loginLog'),
        },
        name: 'LoginLog',
        path: '/system/login-log',
        component: () => import('#/views/system/log/login/index.vue'),
      },
    ],
  },
];

export default routes;
