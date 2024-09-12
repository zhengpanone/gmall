package com.zp.framework.test.config;

import jakarta.annotation.Resource;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Author : zhengpanone
 * Date : 2024/8/29 14:38
 * Version : v1.0.0
 * Description:
 */
@SpringJUnitConfig // 替换为你的配置类或使用 @SpringBootTest
public class ConditionalSqlExecutionTest {
    @Resource
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_FILE_PATH = "src/test/resources/sql/clean.sql";

    @Test
    public void someTest() {
        // 测试逻辑
    }

    @AfterEach
    public void executeSqlIfExists() {
        Path path = Paths.get(SQL_FILE_PATH);
        File sqlFile = path.toFile();

        // 检查文件是否存在
        if (sqlFile.exists() && !sqlFile.isDirectory()) {
            try {
                // 读取 SQL 文件内容并执行
                String sql = Files.readString(path);
                jdbcTemplate.execute(sql);
                System.out.println("Executed clean.sql successfully.");
            } catch (Exception e) {
                System.err.println("Error executing clean.sql: " + e.getMessage());
            }
        } else {
            System.out.println("clean.sql does not exist, skipping execution.");
        }
    }
}
