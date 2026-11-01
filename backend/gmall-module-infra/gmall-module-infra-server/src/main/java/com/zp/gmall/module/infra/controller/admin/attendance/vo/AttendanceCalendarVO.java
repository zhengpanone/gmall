package com.zp.gmall.module.infra.controller.admin.attendance.vo;

import lombok.Data;

@Data
public class AttendanceCalendarVO {
    private Boolean needCheckin;
    private Integer dateType;
    private String holidayName;
    private String overtimeRate;
    private String remark;
}
