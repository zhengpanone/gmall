package com.zp.framework.operatelog.core.service;

import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import com.zp.module.system.api.logger.OperateLogApi;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 13:26
 * Version : v1.0.0
 * Description: 操作日志 ILogRecordService 实现类
 *
 *  基于 {@link OperateLogApi} 实现，记录操作日志
 *
 */
@Slf4j
public class LogRecordServiceImpl implements ILogRecordService {
    @Override
    public void record(LogRecord logRecord) {

    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return List.of();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return List.of();
    }
}
