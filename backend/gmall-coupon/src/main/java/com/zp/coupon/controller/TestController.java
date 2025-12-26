package com.zp.coupon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * TestController
 *
 * @author zhengpanone
 * @since 2022-07-30
 */
@RestController
public class TestController {
    @Autowired
    private Environment env;
    @GetMapping("/")
    public String test(){
        return "gmall-coupon";
    }
    @GetMapping("/config")
    public Map<String,String>  getAllEnv(){
        Map<String, String> configs = new HashMap<>();
        MutablePropertySources propertySources = ((AbstractEnvironment) env).getPropertySources();
        for(PropertySource<?> propertySource: propertySources){
            if(propertySource instanceof EnumerablePropertySource){
                for(String name:  ((EnumerablePropertySource<?>) propertySource).getPropertyNames()){
                    if (name != null ) {
                        configs.put(name, env.getProperty(name));
                    }
                }
            }
        }
        return configs;
    }
}
