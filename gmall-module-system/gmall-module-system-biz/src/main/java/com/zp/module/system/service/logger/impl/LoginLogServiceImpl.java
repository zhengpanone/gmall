package com.zp.module.system.service.logger.impl;

import com.zp.framework.common.pojo.PageResult;
import com.zp.framework.common.util.object.BeanUtils;
import com.zp.module.system.api.logger.dto.LoginLogCreateDTO;
import com.zp.module.system.controller.admin.logger.dto.LoginLogPageDTO;
import com.zp.module.system.dal.dataobject.logger.LoginLogDO;
import com.zp.module.system.dao.logger.LoginLogMapper;
import com.zp.module.system.service.logger.LoginLogService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * Author : zhengpanone
 * Date : 2024/8/5 11:18
 * Version : v1.0.0
 * Description: TODO
 */
@Service
public class LoginLogServiceImpl implements LoginLogService {
    @Resource
    private LoginLogMapper loginLogMapper;

    /**
     * 获得登录日志分页
     *
     * @param pageDTO 分页条件
     * @return 登录日志分页
     */
    @Override
    public PageResult<LoginLogDO> getLoginLogPage(LoginLogPageDTO pageDTO) {
        return loginLogMapper.selectPage(pageDTO);
    }

    /**
     * 创建登录日志
     *
     * @param reqDTO 日志信息
     */
    @Override
    public void createLoginLog(LoginLogCreateDTO reqDTO) {
        LoginLogDO loginLog = BeanUtils.toBean(reqDTO, LoginLogDO.class);
        loginLogMapper.insert(loginLog);
    }
}
