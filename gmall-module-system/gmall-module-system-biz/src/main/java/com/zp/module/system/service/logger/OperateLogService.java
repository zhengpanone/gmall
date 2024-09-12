package com.zp.module.system.service.logger;

import com.zp.framework.common.pojo.PageResult;
import com.zp.module.system.api.logger.dto.OperateLogCreateDTO;
import com.zp.module.system.api.logger.dto.OperateLogPageDTO;
import com.zp.module.system.dal.dataobject.logger.OperateLogDO;

/**
 * Author : zhengpanone
 * Date : 2024/8/30 15:10
 * Version : v1.0.0
 * Description: 操作日志 Service 接口
 */
public interface OperateLogService {
    /**
     * 记录操作日志
     *
     * @param createDTO 创建请求
     */
    void createOperateLog(OperateLogCreateDTO createDTO);

    /**
     * 获得操作日志分页列表
     *
     * @param pageReqVO 分页条件
     * @return 操作日志分页列表
     */
    PageResult<OperateLogDO> getOperateLogPage(OperateLogPageDTO pageReqVO);
}
