package com.zp.framework.mybatis.core.util;

import com.baomidou.mybatisplus.annotation.DbType;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 22:32
 * Version : v1.0.0
 * Description: JDBC 工具类
 */
public class JdbcUtils {

    /**
     * 判断连接是否正确
     *
     * @param url      数据源连接
     * @param username 账号
     * @param password 密码
     * @return 是否正确
     */
    public static boolean isConnectionOK(String url, String username, String password) {
        try (Connection ignore = DriverManager.getConnection(url, username, password)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static DbType getDbType(String url) {
        String name = com.alibaba.druid.util.JdbcUtils.getDbType(url, null);
        return DbType.getDbType(name);
    }
}
