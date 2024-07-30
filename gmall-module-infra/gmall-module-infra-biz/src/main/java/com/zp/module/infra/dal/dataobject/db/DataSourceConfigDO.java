package com.zp.module.infra.dal.dataobject.db;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.zp.framework.mybatis.core.type.EncryptTypeHandler;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Author : zhengpanone
 * Date : 2024/7/8 21:01
 * Version : v1.0.0
 * Description: 数据源配置
 */
@TableName(value = "infra_data_source_config", autoResultMap = true)
@KeySequence("infra_data_source_config_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@Accessors(chain = true)
public class DataSourceConfigDO {
    /**
     * 主键编号 - Master 数据源
     */
    public static final String ID_MASTER = "0";

    /**
     * 主键编号
     */
    private String id;
    /**
     * 连接名
     */
    private String name;

    /**
     * 数据源连接
     */
    private String url;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    @TableField(typeHandler = EncryptTypeHandler.class)
    private String password;

}
