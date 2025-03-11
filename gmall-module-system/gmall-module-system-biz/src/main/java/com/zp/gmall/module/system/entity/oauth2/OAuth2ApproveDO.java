package com.zp.gmall.module.system.entity.oauth2;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.mybatis.core.dataobject.BaseDO;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Author : zhengpanone
 * Date : 2025/4/7 18:57
 * Version : v1.0.0
 * Description: OAuth2 批准 DO
 * 用户在 sso.vue 界面时，记录接受的 scope 列表
 */
@TableName(value = "system_oauth2_approve", autoResultMap = true)
@Data
@EqualsAndHashCode(callSuper = true)
public class OAuth2ApproveDO extends BaseDO {
}
