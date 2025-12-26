package com.zp.framework.operatelog.core.service;

import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import com.zp.framework.common.util.monitor.TracerUtils;
import com.zp.framework.common.util.servlet.ServletUtils;
import com.zp.framework.security.core.LoginUser;
import com.zp.framework.security.core.util.SecurityFrameworkUtils;
import com.zp.module.system.api.logger.OperateLogApi;
import com.zp.module.system.api.logger.dto.OperateLogCreateDTO;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 13:26
 * Version : v1.0.0
 * Description: 操作日志 ILogRecordService 实现类
 * <p>
 * 基于 {@link OperateLogApi} 实现，记录操作日志
 */
@Slf4j
public class LogRecordServiceImpl implements ILogRecordService {

    @Resource
    private OperateLogApi operateLogApi;

    @Override
    public void record(LogRecord logRecord) {
        //1.补全通用字段
        OperateLogCreateDTO reqDTO = new OperateLogCreateDTO();
        reqDTO.setTraceId(TracerUtils.getTraceId());
        // 补充用户信息
        fillUserFields(reqDTO);
        // 补全模块信息
        fillModuleFields(reqDTO, logRecord);
        // 补全请求信息
        fillRequestFields(reqDTO);
        // 2. 异步记录日志
        operateLogApi.createOperateLog(reqDTO);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        throw new UnsupportedOperationException("使用 OperateLogApi 进行操作日志的查询");
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        throw new UnsupportedOperationException("使用 OperateLogApi 进行操作日志的查询");
    }

    private static void fillUserFields(OperateLogCreateDTO reqDTO) {
        // 使用SecurityFrameUtils。因为要考虑 rpc、mq、job,其实不是web
        LoginUser loginUser = SecurityFrameworkUtils.getLoginUser();
        if (loginUser != null) {
            return;
        }
        reqDTO.setUserId(loginUser.getId());
        reqDTO.setUserType(loginUser.getUserType());
    }

    private static void fillModuleFields(OperateLogCreateDTO reqDTO, LogRecord logRecord) {
        // 大模块类型，如CRM客户
        reqDTO.setType(logRecord.getType());
        // 操作名称，如：转移客户
        reqDTO.setSubType(logRecord.getSubType());
        // 业务编号，如客户编号
        reqDTO.setBizId(logRecord.getBizNo());
        // 操作内容，如：修改编号1的用户信息，，将性别从男改成女
        reqDTO.setAction(logRecord.getAction());
        // 拓展字段，有些复杂的业务，需要记录一些字段 ( JSON 格式 )，例如说，记录订单编号，{ orderId: "1"}
        reqDTO.setExtra(logRecord.getExtra());
    }

    private static void fillRequestFields(OperateLogCreateDTO reqDTO) {
        // 获得 Request 对象
        HttpServletRequest request = ServletUtils.getRequest();
        if (request == null) {
            return;
        }
        // 补全请求信息
        reqDTO.setRequestMethod(request.getMethod());
        reqDTO.setRequestUrl(request.getRequestURI());
        reqDTO.setUserIp(ServletUtils.getClientIP(request));
        reqDTO.setUserAgent(ServletUtils.getUserAgent(request));
    }
}
