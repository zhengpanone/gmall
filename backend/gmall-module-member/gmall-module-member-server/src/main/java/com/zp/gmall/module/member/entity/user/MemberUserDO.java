package com.zp.gmall.module.member.entity.user;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.gmall.framework.common.enums.CommonStatusEnum;
import com.zp.gmall.framework.tenant.core.db.TenantBaseDO;
import lombok.*;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2026/4/29 22:01
 * Version : v1.0.0
 * Description: 会员用户 DO
 */
@Data
@TableName(value = "member_user", autoResultMap = true)
@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberUserDO extends TenantBaseDO {
    /**
     * 用户ID
     */
    @TableId
    private String id;
    /**
     * 手机
     */
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 状态
     * {@link CommonStatusEnum}
     */
    private String status;

    /**
     * 注册IP
     */
    private String registerIp;

    /**
     * 注册时间
     */
    private String registerTime;

    /**
     * 注册终端
     */
    private String registerTerminal;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 最后登录终端
     */
    private String lastLoginTerminal;

    /**
     * 昵称
     */
    private String nickname;


    /**
     * 头像
     */
    private String avatarUrl;

    /**
     * 性别
     */
    private String sex;

    /**
     * 生日
     */
    private String birthday;

    /**
     * 国家
     */
    private String country;
    /**
     * 省份
     */
    private String province;
    /**
     * 城市
     */
    private String city;
    /**
     * 区域
     */
    private String area;

    /**
     * 地址
     */
    private String address;

    /**
     * 备注
     */
    private String remark;
}
