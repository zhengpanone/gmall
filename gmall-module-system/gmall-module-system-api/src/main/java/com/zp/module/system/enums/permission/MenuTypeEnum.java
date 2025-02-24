package com.zp.module.system.enums.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Optional;

/**
 * Author : zhengpanone
 * Date : 2024/7/31 18:18
 * Version : v1.0.0
 * Description: 菜单类型枚举类
 * 快捷入口 --> 页面
 * 快捷入口 --> 接口
 * 目录 --> 页面
 * 目录 --> 快捷入口
 * 页面 --> 按钮
 * 页面 --> 接口
 * 按钮 --> 接口
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    // 目录
    DIR(1, "目录", "页面或功能的层级结构"),
    // 页面
    PAGE(2, "页面", "具体功能或内容展示的区域"),
    // 按钮
    BUTTON(3, "按钮", "页面中的交互控件，用于触发操作"),
    /**
     * 接口
     */
    API(4, "接口", "前后端通信的通道，完成数据交互或功能调用"),
    /**
     * 快捷入口
     */
    QUICK_ENTRY(5, "快捷入口", "快速访问某个页面或功能"),
    ;
    /**
     * 类型
     */
    private final Integer type;

    // 类型中文名
    private final String displayName;
    // 类型描述
    private final String description;

    /**
     * 根据枚举查找对应类型
     *
     * @param menuTypeEnum 枚举类型
     * @return 对应的类型，未找到返回 Optional.empty()
     */
    public static Optional<Integer> fromEnum(MenuTypeEnum menuTypeEnum) {
        if (menuTypeEnum != null) {
            return Optional.of(menuTypeEnum.getType());
        }
        return Optional.empty();
    }
}
