package com.zp.framework.mybatis.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.zp.framework.mybatis.core.enums.SqlConstants;
import com.zp.framework.mybatis.core.util.JdbcUtils;
import org.springframework.core.env.ConfigurableEnvironment;
import com.zp.framework.common.util.collection.SetUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;


import java.util.Objects;
import java.util.Set;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 22:09
 * Version : v1.0.0
 * Description: 当 IdType 为 {@link IdType#NONE} 时，根据 PRIMARY 数据源所使用的数据库，自动设置
 */
@Slf4j
public class IdTypeEnvironmentPostProcessor implements EnvironmentPostProcessor {
    private static final String ID_TYPE_KEY = "mybatis-plus.global-config.db-config.id-type";

    private static final String DATASOURCE_DYNAMIC_KEY = "spring.datasource.dynamic";

    private static final String QUARTZ_JOB_STORE_DRIVER_KEY = "spring.quartz.properties.org.quartz.jobStore.driverDelegateClass";

    private static final Set<DbType> INPUT_ID_TYPES = SetUtils.asSet(DbType.ORACLE, DbType.ORACLE_12C, DbType.POSTGRE_SQL, DbType.KINGBASE_ES, DbType.DB2, DbType.H2);

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 如果获取不到DbType,则不进行处理
        DbType dbType = getDbType(environment);
        if (Objects.isNull(dbType)) {
            return;
        }
        setJobStoreDriverIfPresent(environment, dbType);
        // 初始化 SQL 静态变量
        SqlConstants.init(dbType);
        // 如果非 NONE，则不进行处理
        IdType idType = getIdType(environment);
        if (idType != IdType.NONE) {
            return;
        }
        // 情况一，用户输入 ID，适合 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库
        if (INPUT_ID_TYPES.contains(dbType)) {
            setIdType(environment, idType);
            return;
        }
        // 情况二，自增 ID，适合 MySQL 等直接自增的数据库
        setIdType(environment, IdType.AUTO);

    }


    public IdType getIdType(ConfigurableEnvironment environment) {
        return environment.getProperty(ID_TYPE_KEY, IdType.class);
    }

    public void setIdType(ConfigurableEnvironment environment, IdType idType) {
        environment.getSystemProperties().put(ID_TYPE_KEY, idType);
        log.info("[setIdType][修改 MyBatis Plus 的 idType 为({})]", idType);
    }

    public void setJobStoreDriverIfPresent(ConfigurableEnvironment environment, DbType dbType) {
        String driverClass = environment.getProperty(QUARTZ_JOB_STORE_DRIVER_KEY);
        if (StringUtils.isNotEmpty(driverClass)) {
            return;
        }
        // 根据 dbType 类型，获取对应的 driverClass
        switch (dbType) {
            case POSTGRE_SQL -> driverClass = "org.quartz.impl.jdbcjobstore.PostgreSQLDelegate";
            case ORACLE, ORACLE_12C -> driverClass = "org.quartz.impl.jdbcjobstore.oracle.OracleDelegate";
            case SQL_SERVER, SQL_SERVER2005 -> driverClass = "org.quartz.impl.jdbcjobstore.MSSQLDelegate";
        }
        // 设置 driverClass 变量
        if (StringUtils.isNotEmpty(driverClass)) {
            environment.getSystemProperties().put(QUARTZ_JOB_STORE_DRIVER_KEY, driverClass);
        }
    }

    public static DbType getDbType(ConfigurableEnvironment environment) {
        String primary = environment.getProperty(DATASOURCE_DYNAMIC_KEY + "." + "primary");
        if (StringUtils.isEmpty(primary)) {
            return null;
        }
        String url = environment.getProperty(DATASOURCE_DYNAMIC_KEY + ".datasource." + primary + ".url");
        if (StringUtils.isEmpty(url)) {
            return null;
        }
        return JdbcUtils.getDbType(url);
    }
}
