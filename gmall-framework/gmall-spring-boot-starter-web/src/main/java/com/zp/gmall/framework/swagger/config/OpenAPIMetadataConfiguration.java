package com.zp.gmall.framework.swagger.config;

import lombok.Setter;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Author : zhengpanone
 * Date : 2025/4/10 19:39
 * Version : v1.0.0
 * Description:
 */

public class OpenAPIMetadataConfiguration implements InitializingBean, ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Setter
    private String path;

    @Override
    public void afterPropertiesSet() throws Exception {
        String[] beanNamesForType = applicationContext.getBeanNamesForType(ServiceInstance.class);
        if (beanNamesForType.length == 0) {
            return;
        }
        ServiceInstance serviceInstance = applicationContext.getBean(ServiceInstance.class);
        serviceInstance.getMetadata().put("spring-doc", path);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
