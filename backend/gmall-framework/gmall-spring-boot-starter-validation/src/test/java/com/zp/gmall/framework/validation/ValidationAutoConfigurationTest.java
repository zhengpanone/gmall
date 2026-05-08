package com.zp.gmall.framework.validation;

import com.zp.gmall.framework.validation.config.ValidationAutoConfiguration;
import com.zp.gmall.framework.validation.config.ValidationProperties;
import com.zp.gmall.framework.validation.handler.GlobalValidationExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.boot.test.context.runner.ApplicationContextRunner;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidationAutoConfigurationTest {

    private final ApplicationContextRunner contextRunner = new ApplicationContextRunner()
            .withConfiguration(AutoConfigurations.of(ValidationAutoConfiguration.class));

    @Test
    void shouldLoadDefaultBeans() {
        contextRunner.run(context -> {
            assertTrue(context.containsBean("globalValidationExceptionHandler"));
            assertTrue(context.getBean(GlobalValidationExceptionHandler.class) != null);
            ValidationProperties properties = context.getBean(ValidationProperties.class);
            assertTrue(properties.isXssEnabled());
            assertTrue(properties.isSqlInjectEnabled());
        });
    }

    @Test
    void shouldBindValidationProperties() {
        contextRunner
                .withPropertyValues(
                        "gmall.validation.xss-enabled=false",
                        "gmall.validation.sql-inject-enabled=false"
                )
                .run(context -> {
                    ValidationProperties properties = context.getBean(ValidationProperties.class);
                    assertFalse(properties.isXssEnabled());
                    assertFalse(properties.isSqlInjectEnabled());
                });
    }
}
