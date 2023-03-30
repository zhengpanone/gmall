package com.zp.sys.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.common.entity.BaseEntity;
import lombok.Data;

/**
 *
 *
 * @author zhengpanone
 * @since 2022-06-30
 */
@TableName("sys_user")
@Data
public class User /*extends BaseEntity*/ {
    @TableField("username")
    private String username;
    @TableField("password")
    private String password;
}
