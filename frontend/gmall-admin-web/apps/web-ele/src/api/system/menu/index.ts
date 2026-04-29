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
    id?: number | string;
    name: string;
    permission: string;
    type: MenuTypeEnum | number;
    sort: number;
    parentId: number | string;
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
    parentId?: number | string;
    path?: string;
    icon?: string;
    component?: string;
    componentName?: string;
    status?: number;
    visible?: boolean;
    keepAlive?: boolean;
    alwaysShow?: boolean;
  }

    export interface MenuListParam {
    menuName?: string;
    menuCode?: string;
  }

  /** 更新菜单参数 */
  export interface UpdateMenuParams extends CreateMenuParams {
    id: number | string;
  }
}

/**
 * 获取菜单列表
 */
export async function getMenuList(params: SystemMenuApi.MenuListParam = {}) {
  return backendClient.get<SystemMenuApi.Menu[]>('/system/admin-api/menu/list', { params });
}

/**
 * 获取菜单详情
 */
export async function getMenu(id: number | string) {
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
export async function updateMenu(
  id: number | string,
  data: SystemMenuApi.UpdateMenuParams,
) {
  return backendClient.put(`/system/admin-api/menu/update`, { ...data, id });
}

/**
 * 删除菜单
 */
export async function deleteMenu(id: number | string) {
  return backendClient.delete(`/system/admin-api/menu/delete/${id}`);
}

/**
 * 获取菜单选项列表（用于下拉选择）
 */
export async function getMenuOptions() {
  return backendClient.get<
    Array<{
      children?: any[];
      id: number | string;
      name: string;
      parentId: number | string;
    }>
  >('/system/admin-api/menu/options');
}
