package com.zp.gmall.module.iot.controller.admin.device;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.iot.controller.admin.device.dto.IotDeviceDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * Project: backend
 * <p>
 * Module: com.zp.gmall.module.iot.controller.admin.device
 * <p>
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-04-30
 */
@Tag(name = "管理后台 - IoT 设备")
@RestController
@RequestMapping("/device")
@Validated
public class IotDeviceController {

    @PostMapping("/create")
    @Operation(summary = "创建设备")
    public Result<?> createDevice(@Valid @RequestBody IotDeviceDTO iotDeviceDTO) {
//        deviceService.createDevice(createReqVO);
        return Result.ok();
    }


    @PutMapping("/update")
    @Operation(summary = "更新设备")
    public Result<?> updateDevice(@Valid @RequestBody IotDeviceDTO iotDeviceDTO) {
//        deviceService.updateDevice(updateReqVO);
        return Result.ok();
    }

}
