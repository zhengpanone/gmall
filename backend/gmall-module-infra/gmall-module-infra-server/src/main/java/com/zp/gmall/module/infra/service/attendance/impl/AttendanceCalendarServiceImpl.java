package com.zp.gmall.module.infra.service.attendance.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.module.infra.controller.admin.attendance.dto.AttendanceCalendarUpdateDTO;
import com.zp.gmall.module.infra.controller.admin.attendance.vo.AttendanceCalendarVO;
import com.zp.gmall.module.infra.entity.attendance.AttendanceCalendarDO;
import com.zp.gmall.module.infra.enums.AttendanceCalendarTypeEnum;
import com.zp.gmall.module.infra.mapper.attendance.AttendanceCalendarMapper;
import com.zp.gmall.module.infra.service.attendance.IAttendanceCalendarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class AttendanceCalendarServiceImpl extends ServiceImpl<AttendanceCalendarMapper, AttendanceCalendarDO> implements IAttendanceCalendarService {
    @Override
    public List<AttendanceCalendarVO> getMonthList(int year, int month) {
        return List.of();
    }

    @Override
    public void updateCalendar(AttendanceCalendarUpdateDTO updateDTO) {

    }

    @Override
    public void importHolidayExcel(MultipartFile file) {

    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void generateYearCalendar(int year) {
        // 先删除当年已有数据
        lambdaUpdate().eq(AttendanceCalendarDO::getYear, year).remove();
        LocalDate start = LocalDate.of(year, 1, 1);
        LocalDate end = LocalDate.of(year, 12, 31);
        List<AttendanceCalendarDO> list = new ArrayList<>();
        for (LocalDate date = start; !date.isAfter(end); date = date.plusDays(1)) {
            AttendanceCalendarDO calendarDO = new AttendanceCalendarDO();
            calendarDO.setCalendarDate(date);
            calendarDO.setYear(date.getYear());
            calendarDO.setMonth(date.getMonthValue());
            calendarDO.setWeekDay(date.getDayOfWeek().getValue());
            // 默认规则: 周一到周五上班, 周六周日休息
            if (calendarDO.getWeekDay() == DayOfWeek.SATURDAY.getValue() || calendarDO.getWeekDay() == DayOfWeek.SUNDAY.getValue()) {
                calendarDO.setNeedCheckin(true);
                calendarDO.setDateType(AttendanceCalendarTypeEnum.WORKDAY.getType());
                calendarDO.setOvertimeRate(new BigDecimal("1.5"));
            } else {
                calendarDO.setOvertimeRate(new BigDecimal("2.0"));
                calendarDO.setNeedCheckin(false);
                calendarDO.setDateType(AttendanceCalendarTypeEnum.HOLIDAY.getType());
            }
            list.add(calendarDO);
        }
        saveBatch(list);
    }

    @Override
    public boolean isWorkDay(LocalDate date) {
        AttendanceCalendarVO calendar = getCalendar(date);
        return calendar != null && calendar.getNeedCheckin();
    }

    @Override
    public boolean isHoliday(LocalDate date) {
        AttendanceCalendarVO calendar = getCalendar(date);
        return calendar != null && Objects.equals(calendar.getDateType(), AttendanceCalendarTypeEnum.HOLIDAY.getType());
    }

    @Override
    public AttendanceCalendarVO getCalendar(LocalDate date) {
        AttendanceCalendarDO calendarDO = lambdaQuery().eq(AttendanceCalendarDO::getCalendarDate, date).one();
        return Convert.convert(AttendanceCalendarVO.class, calendarDO);
    }

    @Override
    public long countWorkDays(LocalDate startDate, LocalDate endDate) {
        return lambdaQuery().between(AttendanceCalendarDO::getCalendarDate, startDate, endDate)
                .eq(AttendanceCalendarDO::isNeedCheckin, true)
                .count();

    }
}
