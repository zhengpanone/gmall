package com.zp.gmall.module.system.service.log.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.api.logger.dto.LoginLogDTO;
import com.zp.gmall.module.system.api.logger.dto.LoginLogPageDTO;
import com.zp.gmall.module.system.api.logger.vo.LoginLogVO;
import com.zp.gmall.module.system.entity.log.LoginLogDO;
import com.zp.gmall.module.system.mapper.log.LoginLogMapper;
import com.zp.gmall.module.system.service.log.ILoginLogService;
import org.springframework.stereotype.Service;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Service
public class LoginLogServiceImpl extends ServiceImpl<LoginLogMapper, LoginLogDO> implements ILoginLogService {
    @Override
    public void create(LoginLogDTO dto) {

    }

    @Override
    public LoginLogVO getById(String id) {
        return null;
    }

    @Override
    public PageResult<LoginLogVO> getPageList(LoginLogPageDTO dto) {
        return null;
    }
}
