import { backendClient } from '#/api/request';

export namespace SystemMenuApi {
  /** 菜单类型枚举 */
  export enum MenuTypeEnum {
    DIR = 1, // 目录
    MENU = 2, // 菜单
    BUTTON = 3, // 按钮
  }

  /** 菜单信息 */
  export interface Menu {
    id?: string;
    name: string;
    permission: string;
    type: MenuTypeEnum | number;
    sort: number;
    parentId: number;
    path: string;
    icon: string;
    component: string;
    componentName?: string;
    status: number;
    visible: boolean;
    keepAlive: boolean;
    alwaysShow?: boolean;
    createTime?: Date;
  }

  /** 创建菜单参数 */
  export interface CreateMenuParams {
    name: string;
    permission?: string;
    type: number;
    sort?: number;
    parentId?: number;
    path?: string;
    icon?: string;
    component?: string;
    componentName?: string;
    status?: number;
    visible?: boolean;
    keepAlive?: boolean;
    alwaysShow?: boolean;
  }

  /** 更新菜单参数 */
  export interface UpdateMenuParams extends CreateMenuParams {
    id: string;
  }
}

/**
 * 获取菜单列表
 */
export async function getMenuList() {
  return backendClient.get<SystemMenuApi.Menu[]>('/system/admin-api/menu/list');
}

/**
 * 获取菜单详情
 */
export async function getMenu(id: string) {
  return backendClient.get<SystemMenuApi.Menu>(`/system/admin-api/menu/${id}`);
}

/**
 * 创建菜单
 */
export async function createMenu(data: SystemMenuApi.CreateMenuParams) {
  return backendClient.post('/system/admin-api/menu/create', data);
}

/**
 * 更新菜单
 */
export async function updateMenu(id: string, data: SystemMenuApi.UpdateMenuParams) {
  return backendClient.put(`/system/admin-api/menu/update`, { ...data, id });
}

/**
 * 删除菜单
 */
export async function deleteMenu(id: string) {
  return backendClient.delete(`/system/admin-api/menu/delete/${id}`);
}

/**
 * 获取菜单选项列表（用于下拉选择）
 */
export async function getMenuOptions() {
  return backendClient.get<Array<{ children?: any[]; id: number; name: string; parentId: number; }>>('/system/admin-api/menu/options');
}
