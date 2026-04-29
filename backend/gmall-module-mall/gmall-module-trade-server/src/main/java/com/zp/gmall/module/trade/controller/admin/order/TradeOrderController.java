package com.zp.gmall.module.trade.controller.admin.order;

import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.trade.controller.admin.order.dto.TradeOrderPageDTO;
import com.zp.gmall.module.trade.controller.admin.order.vo.TradeOrderPageItemVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : zhengpanone
 * Date : 2026/4/30 00:16
 * Version : v1.0.0
 * Description:
 */
@Tag(name = "管理后台 - 交易订单")
@RestController
@RequestMapping("/order")
@Validated
@Slf4j
public class TradeOrderController {

    @GetMapping("/page")
    @Operation(summary = "获得交易订单分页")
    public PageResult<TradeOrderPageItemVO> getOrderPage(TradeOrderPageDTO reqVO) {

        return PageResult.empty();
    }

}
