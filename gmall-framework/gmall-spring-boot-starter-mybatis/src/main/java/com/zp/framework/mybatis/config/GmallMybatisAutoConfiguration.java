package com.zp.framework.mybatis.config;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.*;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.zp.framework.mybatis.core.handler.DefaultDBFieldHandler;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.ConfigurableEnvironment;

import java.util.Objects;

/**
 * Author : zhengpanone
 * Date : 2024/7/7 21:40
 * Version : v1.0.0
 * Description: Mybatis配置类
 */
// 目的：先于 MyBatis Plus 自动配置，避免 @MapperScan 可能扫描不到 Mapper 打印 warn 日志
@AutoConfiguration(before = MybatisPlusAutoConfiguration.class)
// Mapper 懒加载，目前仅用于单元测试
@MapperScan(value = "${gmall.info.base-package}", annotationClass = Mapper.class,
        lazyInitialization = "${mybatis-lazy-initialization:false}")
public class GmallMybatisAutoConfiguration {
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        // 分页插件
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return mybatisPlusInterceptor;
    }

    @Bean
    public MetaObjectHandler defaultMetaObjectHandler() {
        // 自动填充参数
        return new DefaultDBFieldHandler();
    }

    @Bean
    @ConditionalOnProperty(prefix = "mybatis-plus.global-config.db-config", name = "id-type", havingValue = "INPUT")
    public IKeyGenerator keyGenerator(ConfigurableEnvironment environment) {
        DbType dbType = IdTypeEnvironmentPostProcessor.getDbType(environment);
        if (Objects.nonNull(dbType)) {
            switch (dbType) {
                case POSTGRE_SQL -> new PostgreKeyGenerator();
                case ORACLE, ORACLE_12C -> new OracleKeyGenerator();
                case H2 -> new H2KeyGenerator();
                case KINGBASE_ES -> new KingbaseKeyGenerator();
                case DM -> new DmKeyGenerator();
            }
        }
        // 找不到合适的 IKeyGenerator 实现类
        throw new IllegalArgumentException(StrUtil.format("找不到合适的 IKeyGenerator 实现类", dbType));
    }
}
