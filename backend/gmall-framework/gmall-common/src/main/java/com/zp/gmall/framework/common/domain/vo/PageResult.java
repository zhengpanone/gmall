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
@Schema(name = "PageResult", description = "分页数据")
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

    /**
     * 总记录数
     */
    @Schema(description = "总记录数", requiredMode = Schema.RequiredMode.REQUIRED)
    private Long total;

    /**
     * 总页数
     */
    @Schema(description = "总页数")
    private Long pages;

    /**
     * 当前页码
     */
    @Schema(description = "当前页码", example = "1")
    private Long pageNum = 1L;

    /**
     * 每页大小
     */
    @Schema(description = "每页大小", example = "10")
    private Long pageSize = 10L;

    /**
     * 是否有下一页
     */
    @Schema(description = "是否有下一页")
    private Boolean hasNext = false;

    /**
     * 是否有上一页
     */
    @Schema(description = "是否有上一页")
    private Boolean hasPrevious = false;

    /**
     * 数据列表
     */
    @Schema(description = "数据")
    private List<T> list;

    /**
     * 完整分页构造器
     *
     * @param list     列表数据
     * @param total    总记录数
     * @param pageNum  当前页码
     * @param pageSize 每页大小
     */
    public PageResult(List<T> list, Long total, Long pageNum, Long pageSize) {
        this.list = list != null ? list : Collections.emptyList();
        this.total = total != null ? total : 0L;
        this.pageNum = pageNum != null ? pageNum : 1L;
        this.pageSize = pageSize != null ? pageSize : 10L;
        this.pages = calculatePages(this.total, this.pageSize);
        this.hasNext = hasNext(this.pageNum, this.pages);
        this.hasPrevious = hasPrevious(this.pageNum);
    }

    /**
     * 基础分页构造器
     *
     * @param list  列表数据
     * @param total 总记录数
     */
    public PageResult(List<T> list, Long total) {
        this.list = list != null ? new ArrayList<>(list) : new ArrayList<>();
        this.total = total != null ? total : 0L;
    }

    public PageResult(Long total) {
        this.list = new ArrayList<>();
        this.total = total != null ? total : 0L;
    }

    /**
     * 计算总页数
     */
    private static Long calculatePages(Long total, Long pageSize) {
        if (total == null || pageSize == null || pageSize <= 0) {
            return 0L;
        }
        return (total + pageSize - 1) / pageSize;
    }

    /**
     * 是否有下一页
     */
    private static Boolean hasNext(Long pageNum, Long pages) {
        if (pageNum == null || pages == null) {
            return false;
        }
        return pageNum < pages;
    }

    /**
     * 是否有上一页
     */
    private static Boolean hasPrevious(Long pageNum) {
        if (pageNum == null) {
            return false;
        }
        return pageNum > 1;
    }

    /**
     * 创建空分页结果
     */
    public static <T> PageResult<T> empty() {
        return new PageResult<>(0L);
    }

    /**
     * 创建指定总数的空分页结果
     */
    public static <T> PageResult<T> empty(Long total) {
        return new PageResult<>(total);
    }

    /**
     * 创建指定分页参数的空分页结果
     */
    public static <T> PageResult<T> empty(Long pageNum, Long pageSize) {
        PageResult<T> result = new PageResult<>(Collections.emptyList(), 0L, pageNum, pageSize);
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMessage());
        return result;
    }

    /**
     * 创建成功的空分页结果
     */
    public static PageResult<?> ok() {
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), 0L, Collections.emptyList());
    }

    public static <T> PageResult<T> ok(Long total, List<T> list) {
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), total, list);
    }

    public static <T> PageResult<T> ok(Long total, Long pageNum, Long pageSize, List<T> list) {
        return instance(ResultEnum.SUCCESS.getCode(), ResultEnum.SUCCESS.getMessage(), total, pageNum, pageSize, list);
    }

    public static <T> PageResult<T> instance(Integer code, String message, Long total, List<T> list) {
        PageResult<T> result = new PageResult<>();
        result.setCode(code);
        result.setMsg(message);
        result.setTotal(total);
        result.setList(list);
        return result;
    }

    public static <T> PageResult<T> instance(Integer code, String message, Long total, Long pageNum, Long pageSize, List<T> list) {
        PageResult<T> result = new PageResult<>(list, total, pageNum, pageSize);
        result.setCode(code);
        result.setMsg(message);
        return result;
    }

}