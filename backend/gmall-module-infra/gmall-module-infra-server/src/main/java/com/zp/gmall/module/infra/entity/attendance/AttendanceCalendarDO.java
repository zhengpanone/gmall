package com.zp.gmall.module.infra.entity.attendance;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@TableName("t_attendance_calendar")
@Data
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AttendanceCalendarDO extends BaseDO {
    private String id;
    private LocalDate calendarDate;
    private Integer dateType;
    private String holidayName;
    private Integer year;
    private Integer month;
    private Integer weekDay;
    private boolean needCheckin;
    private BigDecimal overtimeRate;

    private String remark;
}
