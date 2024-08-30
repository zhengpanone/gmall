package com.zp.framework.datapermission.config;

import com.zp.framework.datapermission.core.rule.dept.DeptDataPermissionRule;
import com.zp.framework.datapermission.core.rule.dept.DeptDataPermissionRuleCustomizer;
import com.zp.framework.security.core.LoginUser;
import com.zp.module.system.api.permission.PermissionApi;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 21:19
 * Version : v1.0.0
 * Description: 基于部门的数据权限 AutoConfiguration
 */
@AutoConfiguration
@ConditionalOnClass(LoginUser.class)
@ConditionalOnBean(value = DeptDataPermissionRuleCustomizer.class)
public class GmallDeptDataPermissionAutoConfiguration {
/*    @Bean
    public DeptDataPermissionRule deptDataPermissionRule(PermissionApi permissionApi, List<DeptDataPermissionRuleCustomize> customizeList) {
        // Cloud 专属逻辑：优先使用本地的 PermissionApi 实现类，而不是 Feign 调用
        // 原因：在创建租户时，租户还没创建好，导致 Feign 调用获取数据权限时，报“租户不存在”的错误
    }*/
}
