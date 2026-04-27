package com.zp.gmall.framework.common.domain.vo;

import com.zp.gmall.framework.common.enums.ResultEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 分页工具类
 *
 * @author zhengpanone
 */
@Data
@Schema(description = "分页数据")
@NoArgsConstructor
public final class PageResult<T> implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 编码：0表示成功，其他值表示失败
     */
    @Schema(description = "编码：0表示成功，其他值表示失败")
    private Integer code;
    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    private String msg;

    @Schema(description = "总记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long total;

    @Schema(description = "数据", requiredMode = Schema.RequiredMode.REQUIRED)
    private List<T> list;

    /**
     * 分页
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, Long total) {
        this.list = list;
        this.total = total;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total;
    }

    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

    public static PageResult<Void> ok() {
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), 0L, Collections.emptyList());
    }

    public static <T> PageResult<T> ok(Long total, List<T> list) {
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), total, list);
    }

    public static <T> PageResult<T> instance(Integer code, String message, Long total, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setCode(code);
        result.setMsg(message);
        result.setTotal(total);
        result.setList(list);
        return result;
    }

}