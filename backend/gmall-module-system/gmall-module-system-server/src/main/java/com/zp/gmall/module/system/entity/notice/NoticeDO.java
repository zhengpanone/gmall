package com.zp.gmall.module.system.entity.notice;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.tenant.core.db.TenantBaseDO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serial;
import java.time.LocalDateTime;

/**
 *
 * Description: 通知公告
 *
 * @author zhengpan
 * @version 1.0.0
 * @since 2026-05-08
 */
@Data
@TableName(value = "sys_notice", autoResultMap = true)
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class NoticeDO extends TenantBaseDO {

    @Serial
    private static final long serialVersionUID = 1L;
    /**
     * 通知公告ID
     */
    @TableId
    private String id;

    /**
     * 通知公告标题
     */
    private String title;
    /**
     * 通知公告内容
     */
    private String content;
    /**
     * 通知公告状态
     */
    private String status;
    /**
     * 通知公告类型
     * 1 系统公告
     * 2 业务公告
     */
    private String type;

    /**
     * 备注
     */
    private String remark;
    /**
     * 通知公告发布者
     */
    private String publisher;
    /**
     * 通知公告发布时间
     */
    private LocalDateTime publishTime;
    /**
     * 通知公告发布范围
     */
    private String publishScope;
}
