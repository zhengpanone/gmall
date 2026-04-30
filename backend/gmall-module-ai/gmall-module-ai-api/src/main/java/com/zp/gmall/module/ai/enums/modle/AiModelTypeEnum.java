package com.zp.gmall.module.ai.enums.modle;

import com.zp.gmall.framework.common.core.ArrayValuable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.ai.enums.modle
 * <p>
 * Description:  AI 模型类型的枚举
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Getter
@RequiredArgsConstructor
public enum AiModelTypeEnum implements ArrayValuable<Integer> {
    CHAT(1, "聊天"),
    IMAGE(2, "图像"),
    VOICE(3, "语言"),
    VIDEO(4, "视频"),
    EMBEDDING(5, "向量"),
    RERANK(6, "排序"),
    ;

    /**
     * 类型
     */
    private final Integer type;

    /**
     * 类型名称
     */
    private final String name;

    public static final Integer[] ARRAYS = Arrays.stream(values()).map(AiModelTypeEnum::getType).toArray(Integer[]::new);

    @Override
    public Integer[] array() {
        return ARRAYS;
    }
}
