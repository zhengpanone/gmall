package com.zp.framework.mybatis.core.enums;

import com.baomidou.mybatisplus.annotation.DbType;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 23:33
 * Version : v1.0.0
 * Description: SQL相关常量类
 */
public class SqlConstants {
    public static DbType DB_TYPE;

    public static void init(DbType dbType) {
        DB_TYPE = dbType;
    }
}
