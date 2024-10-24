package com.zp.framework.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import com.zp.framework.core.annotations.OperateLog;

/**
 * Author : zhengpanone
 * Date : 2023/12/16 23:03
 * Version : v1.0.0
 * Description: 操作日志的操作类型
 */
@Getter
@AllArgsConstructor
public enum OperateTypeEnum {
    /**
     * 查询
     * 绝大多数情况下，不会记录查询动作，因为过于大量显得没有意义。
     * 在有需要的时候，通过声明 {@link OperateLog} 注解来记录
     */
    GET(1),
    /**
     * 新增
     */
    CREATE(2),
    /**
     * 修改
     */
    UPDATE(3),
    /**
     * 删除
     */
    DELETE(4),
    /**
     * 导出
     */
    EXPORT(5),
    /**
     * 导入
     */
    IMPORT(6),
    /**
     * 其他
     * 在无法归类时，可以选择使用其它。因为还有操作名可以进一步标识
     */
    OTHER(0);
    /**
     * 类型
     */
    private final Integer type;
}
