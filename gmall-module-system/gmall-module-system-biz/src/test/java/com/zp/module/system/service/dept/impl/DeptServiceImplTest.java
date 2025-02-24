package com.zp.module.system.service.dept.impl;

import com.zp.module.system.controller.admin.dept.dto.DeptSaveDTO;
import com.zp.module.system.dao.dept.DeptMapper;
import com.zp.module.system.service.dept.DeptService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Author : zhengpanone
 * Date : 2024/12/10 21:55
 * Version : v1.0.0
 * Description:
 */
@Slf4j
@Transactional // 每次测试结束时回滚
@ActiveProfiles("unit-test") // 指定加载测试配置文件
@TestPropertySource("classpath:application-unit-test.yml")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE,
        classes = DeptServiceImplTest.TestConfig.class,
        properties = {"spring.config.location=classpath:/application-unit-test.yml",
                "spring.autoconfigure.exclude=org.springframework.cloud.openfeign.FeignAutoConfiguration"})
// 禁用 Web 环境
@TestConfiguration // 定义自定义配置类
class DeptServiceImplTest {
    @Resource
    private DeptService deptService;

    @TestConfiguration // 仅加载需要的 Bean
    static class TestConfig {

        @Bean
        public DeptService deptService(DeptMapper deptMapper) {
            return new DeptServiceImpl(deptMapper); // 使用定制的 UserRepository Bean
        }
    }

    @Test
    void createDept() {
        DeptSaveDTO deptSaveDTO = new DeptSaveDTO();
        deptSaveDTO.setName("根部门");
        String deptId = deptService.createDept(deptSaveDTO);
        log.info("创建部门成功，部门编号：{}", deptId);
    }


}