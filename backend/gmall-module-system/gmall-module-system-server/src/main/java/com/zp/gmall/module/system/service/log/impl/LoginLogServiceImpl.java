package com.zp.gmall.module.system.service.log.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
}
