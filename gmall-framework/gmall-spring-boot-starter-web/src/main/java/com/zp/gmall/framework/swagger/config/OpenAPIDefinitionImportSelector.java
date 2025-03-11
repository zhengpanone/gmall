package com.zp.gmall.framework.swagger.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 19:44
 * Version : v1.0.0
 * Description:
 */
public class OpenAPIDefinitionImportSelector implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata metadata, BeanDefinitionRegistry registry) {
        BeanDefinitionBuilder openAPIMetadata = BeanDefinitionBuilder.genericBeanDefinition(OpenAPIMetadataConfiguration.class);

        registry.registerBeanDefinition("openAPIMetadata", openAPIMetadata.getBeanDefinition());

    }
}
