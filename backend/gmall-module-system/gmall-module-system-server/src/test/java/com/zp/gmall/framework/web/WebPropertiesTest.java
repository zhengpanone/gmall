package com.zp.gmall.framework.web;

import com.zp.gmall.framework.web.config.WebProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(classes = {WebProperties.class})
class WebPropertiesTest {

    @Autowired
    private WebProperties webProperties;

    @Test
    @DisplayName("测试配置加载")
    void testPropertiesLoading() {
        assertNotNull(webProperties);
        System.out.println("配置类: " + webProperties.getClass());
        System.out.println("Admin API: " + webProperties.getAdminApi());
        System.out.println("App API: " + webProperties.getAppApi());
    }

    @Test
    @DisplayName("测试 application.yml 配置")
    void testYamlConfig(@Value("${gmall.web.admin-ui.url}") String adminUi) {
        System.out.println("从YAML读取 - adminUi: " + adminUi);
//        assertEquals(adminUi, webProperties.getAdminUi().getUrl());
    }
}
