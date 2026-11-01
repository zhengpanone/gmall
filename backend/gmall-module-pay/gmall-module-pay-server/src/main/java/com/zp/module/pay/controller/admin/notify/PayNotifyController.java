package com.zp.module.pay.controller.admin.notify;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(name = "管理后台 - 回调通知")
@RestController
@RequestMapping("/notify")
@Validated
@Slf4j
public class PayNotifyController {

    @PostMapping(value = "/order/{channelId}")
    @Operation(summary = "支付渠道的统一【支付】回调")
    public String notifyOrder(@PathVariable("channelId") Long channelId,
                              @RequestParam(required = false) Map<String, String> params,
                              @RequestBody(required = false) String body,
                              @RequestHeader Map<String, String> headers) {
        log.info("[notifyOrder][channelId({}) 回调数据({}/{})]", channelId, params, body);
//        // 1. 校验支付渠道是否存在
//        PayClient payClient = channelService.getPayClient(channelId);
//        if (payClient == null) {
//            log.error("[notifyOrder][渠道编号({}) 找不到对应的支付客户端]", channelId);
//            throw exception(CHANNEL_NOT_FOUND);
//        }
//
//        // 2. 解析通知数据
//        PayOrderRespDTO notify = payClient.parseOrderNotify(params, body, headers);
//        orderService.notifyOrder(channelId, notify);
        return "success";
    }
}
