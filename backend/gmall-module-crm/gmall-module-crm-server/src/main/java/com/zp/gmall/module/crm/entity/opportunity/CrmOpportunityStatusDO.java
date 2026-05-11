package com.zp.gmall.module.crm.entity.opportunity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:31
 * Version : v1.0.0
 * Description: CRM 商机状态
 */
@TableName("crm_opportunity")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmOpportunityStatusDO extends BaseDO {

    private String id;
}
