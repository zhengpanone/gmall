package com.zp.framework.operatelog.config;

import com.mzt.logapi.service.ILogRecordService;
import com.mzt.logapi.starter.annotation.EnableLogRecord;
import com.zp.framework.operatelog.core.service.LogRecordServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

/**
 * Author : zhengpanone
 * Date : 2024/8/30 09:48
 * Version : v1.0.0
 * Description: 操作日志配置类
 */
@EnableLogRecord(tenant = "") // 貌似用不上 tenant 这玩意给个空好啦
@AutoConfiguration
@Slf4j
public class GmallOperateLogConfiguration {
    @Bean
    @Primary
    public ILogRecordService iLogRecordServiceImpl() {
        return new LogRecordServiceImpl();
    }
}
