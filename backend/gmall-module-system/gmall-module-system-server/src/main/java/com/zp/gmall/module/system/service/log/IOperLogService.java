package com.zp.gmall.module.system.service.log;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.gmall.framework.common.domain.dto.Ids;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.system.controller.admin.log.dto.OperLogPageDTO;
import com.zp.gmall.module.system.controller.admin.log.vo.OperLogVO;
import com.zp.gmall.module.system.entity.log.OperLogDO;

/**
 *
 * Description:
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
public interface IOperLogService extends IService<OperLogDO> {
    PageResult<OperLogVO> getOperLogPage(OperLogPageDTO dto);

    void deleteOperLog(Ids ids);

    void cleanOperLog();
}
