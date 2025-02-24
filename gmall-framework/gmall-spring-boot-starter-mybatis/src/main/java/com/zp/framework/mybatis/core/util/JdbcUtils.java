package com.zp.framework.mybatis.core.util;

import com.baomidou.dynamic.datasource.DynamicRoutingDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.zp.framework.common.util.spring.SpringUtils;
import com.zp.framework.mybatis.core.enums.DbTypeEnum;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

    /**
     * 获得 URL 对应的 DB 类型
     *
     * @param url URL
     * @return DB 类型
     */
    public static DbType getDbType(String url) {
        return com.baomidou.mybatisplus.extension.toolkit.JdbcUtils.getDbType(url);
    }

    /**
     * 通过当前数据库连接获得对应的 DB 类型
     *
     * @return DB 类型
     */
    public static DbType getDbType() {
        DynamicRoutingDataSource dynamicRoutingDataSource = SpringUtils.getBean(DynamicRoutingDataSource.class);
        DataSource dataSource = dynamicRoutingDataSource.determineDataSource();
        try (Connection conn = dataSource.getConnection()) {
            return DbTypeEnum.find(conn.getMetaData().getDatabaseProductName());
        } catch (SQLException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }
}
