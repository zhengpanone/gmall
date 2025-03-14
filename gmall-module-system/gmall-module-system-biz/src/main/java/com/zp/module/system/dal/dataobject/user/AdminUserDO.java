package com.zp.module.system.dal.dataobject.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.common.enums.CommonStatusEnum;
import com.zp.framework.mybatis.core.type.JsonLongSetTypeHandler;
import com.zp.framework.mybatis.core.type.JsonStringSetTypeHandler;
import com.zp.framework.tenant.core.db.TenantBaseDO;
import com.zp.module.system.enums.common.SexEnum;
import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2023/12/20 15:09
 * Version : v1.0.0
 * 管理后台的用户 DO
 */
@EqualsAndHashCode(callSuper = true)
@TableName(value = "sys_users", autoResultMap = true) // 由于 SQL Server 的 system_user 是关键字，所以使用 system_users
@Data
@Accessors(chain = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserDO extends TenantBaseDO {
    /**
     * 用户ID
     */
    @TableId
    private String id;
    /**
     * 用户账号
     */
    private String username;
    /**
     * 加密后的密码
     * 因为目前使用 {@link BCryptPasswordEncoder} 加密器，所以无需自己处理 salt 盐
     */
    private String password;
    /**
     * 用户昵称
     */
    private String nickname;
    /**
     * 备注
     */
    private String remark;
    /**
     * 部门ID
     */
    private String deptId;
    /**
     * 岗位编号数组
     */
    @TableField(typeHandler =  JsonStringSetTypeHandler.class)
    private Set<String> postIds;
    /**
     * 用户邮箱
     */
    private String email;
    /**
     * 手机号码
     */
    private String mobile;
    /**
     * 用户性别
     * 枚举类 {@link SexEnum}
     */
    private Integer sex;
    /**
     * 用户头像
     */
    private String avatar;
    /**
     * 账号状态
     * 枚举 {@link CommonStatusEnum}
     */
    private Integer status;
    /**
     * 最后登录IP
     */
    private String loginIp;
    /**
     * 最后登录时间
     */
    private LocalDateTime loginDate;


}
