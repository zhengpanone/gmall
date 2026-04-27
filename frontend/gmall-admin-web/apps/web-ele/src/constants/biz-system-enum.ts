// ========== SYSTEM 模块 ==========
/**
 * 菜单的类型枚举
 */
export const SystemMenuTypeEnum = {
  DIR: 1, // 目录
  MENU: 2, // 菜单
  BUTTON: 3, // 按钮
};

/**
 * 菜单类型选项
 */
export const SystemMenuTypeOptions = [
  { label: '目录', value: SystemMenuTypeEnum.DIR, color: 'processing' },
  { label: '菜单', value: SystemMenuTypeEnum.MENU, color: 'default' },
  { label: '按钮', value: SystemMenuTypeEnum.BUTTON, color: 'error' },
];

/**
 * 菜单状态选项
 */
export const SystemMenuStatusOptions = [
  { label: '启用', value: 1, color: 'success' },
  { label: '禁用', value: 0, color: 'default' },
];
