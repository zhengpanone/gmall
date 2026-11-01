package com.zp.gmall.module.infra.service.attendance;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.module.infra.controller.admin.attendance.dto.AttendanceCalendarUpdateDTO;
import com.zp.gmall.module.infra.controller.admin.attendance.vo.AttendanceCalendarVO;
import com.zp.gmall.module.infra.entity.attendance.AttendanceCalendarDO;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;

public interface IAttendanceCalendarService extends IService<AttendanceCalendarDO> {

    /**
     * 获得月份日历列表
     *
     * @param year  年份
     * @param month 月份
     * @return 日历列表
     */
    List<AttendanceCalendarVO> getMonthList(int year, int month);

    /**
     * 更新日历
     *
     * @param updateDTO 更新信息
     */
    void updateCalendar(AttendanceCalendarUpdateDTO updateDTO);

    /**
     * 导入节假日Excel
     *
     * @param file 文件
     */
    void importHolidayExcel(MultipartFile file);

    /**
     * 生成年份日历
     *
     * @param year 年份
     * @return 日历列表
     */
    void generateYearCalendar(int year);

    /**
     * 是否是工作日
     *
     * @param date 日期
     * @return 是否是工作日
     */
    boolean isWorkDay(LocalDate date);

    /**
     * 是否是节假日
     *
     * @param date 日期
     * @return 是否是节假日
     */
    boolean isHoliday(LocalDate date);

    /**
     * 获得日历
     *
     * @param date 日期
     * @return 日历
     */
    AttendanceCalendarVO getCalendar(LocalDate date);

    /**
     * 计算工作天数
     *
     * @param startDate 开始日期
     * @param endDate   结束日期
     * @return 工作天数
     */
    long countWorkDays(LocalDate startDate, LocalDate endDate);

}
