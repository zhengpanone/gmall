package com.zp.gmall.module.system.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.api.logger.dto.LoginLogDTO;
import com.zp.gmall.module.system.api.logger.dto.LoginLogPageDTO;
import com.zp.gmall.module.system.api.logger.vo.LoginLogVO;
import com.zp.gmall.module.system.entity.log.LoginLogDO;

/**
 *
 * Description: 登录日志服务接口
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface ILoginLogService extends IService<LoginLogDO> {

    /**
     * 创建登录日志
     *
     * @param dto 登录日志DTO
     */
    void create(LoginLogDTO dto);

    /**
     * 获取登录日志
     *
     * @param id 登录日志ID
     * @return 登录日志
     */
    LoginLogVO getById(String id);

    /**
     * 分页查询登录日志
     * @param dto
     * @return
     */
    PageResult<LoginLogVO> getPageList(LoginLogPageDTO dto);
}
