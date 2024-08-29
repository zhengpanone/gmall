package com.zp.module.system.service.logger;

import com.zp.framework.common.pojo.PageResult;
import com.zp.module.system.api.logger.dto.LoginLogCreateDTO;
import com.zp.module.system.controller.admin.logger.dto.LoginLogPageDTO;
import com.zp.module.system.dal.dataobject.logger.LoginLogDO;
import jakarta.validation.Valid;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 11:11
 * Version : v1.0.0
 * Description: 登录日志 Service 接口
 */
public interface LoginLogService {
    /**
     * 获得登录日志分页
     *
     * @param pageReqVO 分页条件
     * @return 登录日志分页
     */
    PageResult<LoginLogDO> getLoginLogPage(LoginLogPageDTO pageReqVO);

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    void createLoginLog(@Valid LoginLogCreateDTO reqDTO);

}
