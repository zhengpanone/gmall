package com.zp.gmall.module.trade.order.interfaces.controller;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.trade.order.application.command.CreateOrderCommand;
import com.zp.gmall.module.trade.order.application.service.OrderAppService;
import com.zp.gmall.module.trade.order.interfaces.dto.CreateOrderRequest;
import com.zp.gmall.module.trade.order.interfaces.dto.PayOrderRequest;
import com.zp.gmall.module.trade.order.interfaces.dto.ShipOrderRequest;
import com.zp.gmall.module.trade.order.interfaces.vo.OrderDetailVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 订单 REST 控制器（接口层）。
 *
 * <h3>职责</h3>
 * <ul>
 *   <li>协议适配：将 HTTP 请求转换为应用层 DTO</li>
 *   <li>参数校验：通过 @Validated 进行基本验证</li>
 *   <li>响应封装：将领域结果包装为统一 Result</li>
 *   <li>不包含任何业务逻辑</li>
 * </ul>
 */
@Tag(name = "订单服务 - 核心接口")
@RestController
@RequestMapping("/api/v1/orders")
@Validated
public class OrderController {

    private final OrderAppService orderAppService;

    public OrderController(OrderAppService orderAppService) {
        this.orderAppService = orderAppService;
    }

    /**
     * 创建订单
     */
    @PostMapping
    @Operation(summary = "创建订单")
    public Result<Long> createOrder(@RequestBody @Validated CreateOrderRequest request) {
        CreateOrderCommand command = toCommand(request);
        Long orderId = orderAppService.createOrder(command);
        return Result.ok(orderId);
    }

    /**
     * 支付订单
     */
    @PostMapping("/{orderId}/pay")
    @Operation(summary = "支付订单")
    public Result<Void> payOrder(@PathVariable Long orderId,
                                  @RequestBody @Validated PayOrderRequest request) {
        orderAppService.payOrder(orderId, request.getPaymentNo());
        return Result.ok();
    }

    /**
     * 取消订单
     */
    @PostMapping("/{orderId}/cancel")
    @Operation(summary = "取消订单")
    public Result<Void> cancelOrder(@PathVariable Long orderId,
                                     @RequestParam(required = false) String reason) {
        orderAppService.cancelOrder(orderId, reason);
        return Result.ok();
    }

    /**
     * 发货
     */
    @PostMapping("/{orderId}/ship")
    @Operation(summary = "订单发货")
    public Result<Void> shipOrder(@PathVariable Long orderId,
                                   @RequestBody @Validated ShipOrderRequest request) {
        orderAppService.shipOrder(orderId, request.getTrackingNo(), request.getLogisticsCompany());
        return Result.ok();
    }

    /**
     * 查询订单详情
     */
    @GetMapping("/{orderId}")
    @Operation(summary = "查询订单详情")
    public Result<OrderDetailVO> getOrderDetail(@PathVariable Long orderId) {
        var order = orderAppService.getOrderDetail(orderId);
        return Result.ok(toDetailVO(order));
    }

    // ==================== 私有转换方法 ====================

    private CreateOrderCommand toCommand(CreateOrderRequest request) {
        CreateOrderCommand cmd = new CreateOrderCommand();
        cmd.setMemberId(request.getMemberId());

        if (request.getItems() != null) {
            cmd.setItems(request.getItems().stream().map(item -> {
                CreateOrderCommand.OrderItemCommand itemCmd = new CreateOrderCommand.OrderItemCommand();
                itemCmd.setSkuId(item.getSkuId());
                itemCmd.setProductName(item.getProductName());
                itemCmd.setProductImage(item.getProductImage());
                itemCmd.setQuantity(item.getQuantity());
                itemCmd.setUnitPrice(item.getUnitPrice());
                return itemCmd;
            }).toList());
        }

        cmd.setReceiverName(request.getReceiverName());
        cmd.setReceiverPhone(request.getReceiverPhone());
        cmd.setReceiverProvince(request.getReceiverProvince());
        cmd.setReceiverCity(request.getReceiverCity());
        cmd.setReceiverRegion(request.getReceiverRegion());
        cmd.setReceiverDetail(request.getReceiverDetail());
        cmd.setFreightAmount(request.getFreightAmount());
        cmd.setDiscountAmount(request.getDiscountAmount());
        cmd.setRemark(request.getRemark());
        return cmd;
    }

    private OrderDetailVO toDetailVO(com.zp.gmall.module.trade.order.domain.model.order.Order order) {
        OrderDetailVO vo = new OrderDetailVO();
        vo.setOrderId(order.getId().getValue());
        vo.setOrderNo(order.getOrderNo());
        vo.setStatus(order.getStatus().name());
        vo.setStatusDesc(order.getStatus().getDescription());
        vo.setTotalAmount(order.getTotalAmount().getAmount().toPlainString());
        vo.setPayAmount(order.getPayAmount().getAmount().toPlainString());

        if (order.getItems() != null) {
            vo.setItems(order.getItems().stream().map(item -> {
                OrderDetailVO.ItemVO itemVO = new OrderDetailVO.ItemVO();
                itemVO.setSkuId(item.getSkuId());
                itemVO.setProductName(item.getProductName());
                itemVO.setQuantity(item.getQuantity());
                itemVO.setUnitPrice(item.getUnitPrice().getAmount().toPlainString());
                return itemVO;
            }).toList());
        }

        if (order.getReceiverAddress() != null) {
            var addr = order.getReceiverAddress();
            vo.setReceiverAddress(addr.getProvince() + addr.getCity() + addr.getRegion() + addr.getDetail());
        }

        vo.setCreateTime(order.getCreateTime());
        vo.setPaidTime(order.getPaidTime());
        vo.setShippedTime(order.getShippedTime());
        return vo;
    }
}
