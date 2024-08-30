package com.zp.framework.mybatis.core.type;

import com.baomidou.mybatisplus.extension.handlers.AbstractJsonTypeHandler;
import com.fasterxml.jackson.core.type.TypeReference;
import com.zp.framework.common.util.json.JsonUtils;

import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 17:09
 * Version : v1.0.0
 * Description:
 * <p>
 * 参考 {@link com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler} 实现
 * 在我们将字符串反序列化为 Set 并且泛型为 Long 时，如果每个元素的数值太小，会被处理成 Integer 类型，导致可能存在隐性的 BUG。
 * <p>
 * 例如说哦，SysUserDO 的 postIds 属性
 */

public class JsonStringSetTypeHandler extends AbstractJsonTypeHandler<Object> {

    private static final TypeReference<Set<String>> TYPE_REFERENCE = new TypeReference<>() {
    };

    @Override
    protected Object parse(String json) {
        return JsonUtils.parseObject(json, TYPE_REFERENCE);
    }

    @Override
    protected String toJson(Object obj) {
        return JsonUtils.toJsonString(obj);
    }
}
