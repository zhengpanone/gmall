package com.zp.gmall.module.crm.entity.followup;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.crm.enums.DictTypeConstants;
import lombok.*;

import java.io.Serial;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:34
 * Version : v1.0.0
 * Description: CRM 跟进记录
 */
@TableName(value = "crm_follow_up_record", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmFollowUpRecordDO extends BaseDO {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private String id;

    /**
     * 客户id
     */
    private String customerId;

    /**
     * 跟进内容
     */
    private String followUpContent;

    /**
     * 跟进时间
     */
    private LocalDateTime followUpTime;

    /**
     * 跟进人
     */
    private String followUpPerson;

    /**
     * 跟进结果
     */
    private String followUpResult;

    /**
     * 跟进记录类型
     * {@link DictTypeConstants#CRM_FOLLOW_UP_TYPE}
     */
    private String followUpType;

    /**
     * 跟进记录状态
     */
    private String followUpStatus;

    /**
     * 跟进记录备注
     */
    private String followUpRemark;

    /**
     * 跟进记录图片
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> picUrls;

    /**
     * 跟进记录文件
     */
    @TableField(typeHandler = JacksonTypeHandler.class)
    private List<String> fileUrls;

    /**
     * 下次跟进时间
     */
    private LocalDateTime nextFollowUpTime;

    /**
     * 下次跟进内容
     */
    private String nextFlowUpContent;
}
