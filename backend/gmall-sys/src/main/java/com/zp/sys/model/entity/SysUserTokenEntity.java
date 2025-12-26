package com.zp.sys.model.entity;

import com.mybatisflex.annotation.Column;
import com.mybatisflex.annotation.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户Token
 */
@Data
@Table("sys_user_token")
public class SysUserTokenEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户token
     */
    private String token;
    /**
     * 过期时间
     */
    private Date expireDate;
    /**
     * 更新时间
     */
    @Column(onUpdateValue = "now()")
    private Date updateDate;


}