package com.zp.gmall.module.crm.controller.admin.followup.dto;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.crm.enums.DictTypeConstants;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 23:09
 * Version : v1.0.0
 * Description: 跟进记录
 */
@Data
public class CrmFollowUpRecordDTO implements Serializable {

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
    private String content;

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
    private String recordType;

    /**
     * 跟进记录状态
     */
    private String recordStatus;

    /**
     * 跟进记录备注
     */
    private String recordRemark;

    /**
     * 跟进记录图片
     */
    private List<String> picUrls;

    /**
     * 跟进记录文件
     */
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
