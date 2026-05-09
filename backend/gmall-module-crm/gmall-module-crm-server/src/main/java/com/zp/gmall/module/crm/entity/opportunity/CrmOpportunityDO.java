package com.zp.gmall.module.crm.entity.opportunity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import com.zp.gmall.module.crm.entity.customer.CrmCustomerDO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 21:46
 * Version : v1.0.0
 * Description: CRM 商机
 */
@TableName(value = "crm_opportunity", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmOpportunityDO extends BaseDO {

    /**
     * 商机ID
     */
    @TableId
    private String id;

    /**
     * 商机名称
     */
    private String name;
    /**
     * 客户编号
     *
     * 关联 {@link CrmCustomerDO#getId()}
     */
    private Long customerId;

    /**
     * 跟进状态
     */
    private Boolean followUpStatus;
    /**
     * 最后跟进时间
     */
    private LocalDateTime contactLastTime;
    /**
     * 下次联系时间
     */
    private LocalDateTime contactNextTime;

    /**
     * 负责人的用户编号
     *
     * 关联 AdminUserDO 的 id 字段
     */
    private Long ownerUserId;

    /**
     * 商机状态组编号
     *
     *  关联 {@link CrmBusinessStatusTypeDO#getId()}
     */
    private Long statusTypeId;
    /**
     * 商机状态编号
     *
     * 关联 {@link CrmBusinessStatusDO#getId()}
     */
    private Long statusId;
    /**
     * 结束状态
     *
     * 枚举 {@link CrmBusinessEndStatusEnum}
     */
    private Integer endStatus;
    /**
     * 结束时的备注
     */
    private String endRemark;

    /**
     * 预计成交日期
     */
    private LocalDateTime expectedCloseDate;

    /**
     * 实际成交日期
     */
    private LocalDateTime actualCloseDate;
    /**
     * 产品总金额，单位：元
     *
     * productPrice = ∑({@link CrmBusinessProductDO#getTotalPrice()})
     */
    private BigDecimal totalProductPrice;
    /**
     * 整单折扣，百分比
     */
    private BigDecimal discountPercent;
    /**
     * 商机总金额，单位：元
     */
    private BigDecimal totalPrice;
    /**
     * 备注
     */
    private String remark;

    /**
     * 商机来源
     */
    private String source;


}
