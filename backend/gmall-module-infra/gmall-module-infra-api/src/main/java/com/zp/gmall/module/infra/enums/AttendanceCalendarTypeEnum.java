package com.zp.gmall.module.infra.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum AttendanceCalendarTypeEnum {

    WORKDAY(1),
    WEEKEND(2),

    HOLIDAY(3);

    private final Integer type;
}
