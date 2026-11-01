package com.zp.module.bpm.entity.task;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

import java.io.Serial;

/**
 * 流程抄送 DO
 */
@TableName("bpm_process_instance_copy")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BpmProcessInstanceCopyDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId
    private String id;

    /**
     * 流程实例ID
     */
    private String processInstanceId;

    /**
     * 抄送人ID
     */
    private String ccUserId;

    /**
     * 抄送人名称
     */
    private String ccUserName;

    /**
     * 抄送人类型
     */
    private String ccUserType;

    /**
     * 抄送内容
     */
    private String ccContent;
}
