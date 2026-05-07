package com.zp.gmall.module.ai.enums.modle;

import com.zp.gmall.framework.common.core.Valuable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
public enum AiModelTypeEnum implements Valuable<String> {
    CHAT("1", "聊天"),
    IMAGE("2", "图像"),
    VOICE("3", "语言"),
    VIDEO("4", "视频"),
    EMBEDDING("5", "向量"),
    RERANK("6", "排序"),
    ;

    /**
     * 类型
     */
    private final String type;

    /**
     * 类型名称
     */
    private final String name;


    @Override
    public String getValue() {
        return type;
    }
}
