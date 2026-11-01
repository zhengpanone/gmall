package com.zp.gmall.module.crm.entity.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.*;

/**
 * @author : zhengpanone
 * Date : 2026/5/9 22:33
 * Version : v1.0.0
 * Description: CRM 客户
 */
@TableName(value = "crm_customer", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CrmCustomerDO  extends BaseDO {

    private String id;


}
