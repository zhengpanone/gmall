package com.zp.gmall.module.infra.controller.admin.attendance;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.controller.admin.attendance.dto.AttendanceCalendarUpdateDTO;
import com.zp.gmall.module.infra.controller.admin.attendance.vo.AttendanceCalendarVO;
import com.zp.gmall.module.infra.service.attendance.IAttendanceCalendarService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Tag(name = "管理后台 - 假期管理")
@RequestMapping("/attendance/calendar")
@RestController
@Validated
@RequiredArgsConstructor
public class AttendanceCalendarController {

    private final IAttendanceCalendarService attendanceCalendarService;

    @Operation(summary = "获取月度假期日历")
    @RequestMapping("/getMonthCalendar")
    @Parameters({
            @Parameter(name = "year", description = "年份", required = true, example = "2025"),
            @Parameter(name = "month", description = "月份", required = true, example = "01")
    })
    public Result<List<AttendanceCalendarVO>> getMonthCalendar(@RequestParam("year") int year, @RequestParam("month") int month) {
        return Result.ok(attendanceCalendarService.getMonthList(year, month));
    }

    @Operation(summary = "生成年假期日历")
    @PostMapping("/generate")
    public Result<Void> generateYearCalendar(@RequestParam("year") int year) {
        attendanceCalendarService.generateYearCalendar(year);
        return Result.ok();
    }

    @Operation(summary = "更新假期日历")
    @PutMapping("/update")
    public Result<Void> updateCalendar(@RequestBody @Validated AttendanceCalendarUpdateDTO updateDTO) {
        attendanceCalendarService.updateCalendar(updateDTO);
        return Result.ok();
    }

    @Operation(summary = "Excel导入假期日历")
    @PostMapping("/import")
    public Result<Void> importExcel(MultipartFile file) {
        attendanceCalendarService.importHolidayExcel(file);
        return Result.ok();
    }


}
