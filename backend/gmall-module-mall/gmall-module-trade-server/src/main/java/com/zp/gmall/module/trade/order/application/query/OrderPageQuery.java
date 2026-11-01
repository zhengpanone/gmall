package com.zp.gmall.module.trade.order.application.query;

import com.zp.gmall.framework.common.domain.dto.SortablePageParam;

/**
 * 订单分页查询对象（应用层）。
 * 封装分页参数和筛选条件，应用层不暴露 SQL 细节。
 */
public class OrderPageQuery {

    /** 分页参数 */
    private SortablePageParam pageParam;

    /** 按会员ID筛选 */
    private Long memberId;

    /** 按订单状态筛选 */
    private String status;

    public SortablePageParam getPageParam() {
        return pageParam;
    }

    public void setPageParam(SortablePageParam pageParam) {
        this.pageParam = pageParam;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
