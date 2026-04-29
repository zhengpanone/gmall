package com.zp.module.bpm.entity.task;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

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
