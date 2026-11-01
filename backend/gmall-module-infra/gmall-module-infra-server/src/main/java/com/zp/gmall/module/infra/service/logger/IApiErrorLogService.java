package com.zp.gmall.module.infra.service.logger;

import com.zp.gmall.framework.common.biz.infra.logger.dto.ApiErrorLogCreateReqDTO;
import com.zp.gmall.framework.common.domain.vo.PageResult;
import com.zp.gmall.module.infra.controller.admin.logger.dto.ApiAccessLogPageDTO;
import com.zp.gmall.module.infra.entity.logger.ApiErrorLogDO;

/**
 *
 * Description: API 访问日志 Service接口
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-13
 */
public interface IApiErrorLogService {

    /**
     * 创建API访问日志
     *
     * @param createReqDTO 创建请求参数
     */
    void create(ApiErrorLogCreateReqDTO createReqDTO);

    /**
     * 获取API访问日志
     *
     * @param id 日志ID
     * @return API访问日志
     */
    ApiErrorLogDO getById(String id);

    /**
     * 获取API访问日志分页列表
     *
     * @param pageReqDTO 分页请求参数
     * @return API访问日志分页列表
     */
    PageResult<ApiErrorLogDO> getPageList(ApiAccessLogPageDTO pageReqDTO);

    /**
     * 清理API访问日志
     *
     * @param exceedDay   超过多少天就进行清理
     * @param deleteLimit 清理的间隔条数
     * @return 清理的条数
     */
    Integer cleanAccessLog(Integer exceedDay, Integer deleteLimit);
}
