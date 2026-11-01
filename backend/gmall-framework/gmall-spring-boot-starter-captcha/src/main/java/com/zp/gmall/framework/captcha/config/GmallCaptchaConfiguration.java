package com.zp.gmall.framework.captcha.config;

import com.xingyuv.captcha.properties.AjCaptchaProperties;
import com.xingyuv.captcha.service.CaptchaCacheService;
import com.xingyuv.captcha.service.impl.CaptchaServiceFactory;
import com.zp.gmall.framework.captcha.core.service.RedisCaptchaServiceImpl;
import jakarta.annotation.Resource;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.StringRedisTemplate;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 18:39
 * Version : v1.0.0
 
 */
@AutoConfiguration
public class GmallCaptchaConfiguration {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Bean
    public CaptchaCacheService captchaCacheService(AjCaptchaProperties config) {
        // 缓存类型 redis/local/...
        CaptchaCacheService captchaService = CaptchaServiceFactory.getCache(config.getCacheType().name());
        if (captchaService instanceof RedisCaptchaServiceImpl) {
            ((RedisCaptchaServiceImpl) captchaService).setStringRedisTemplate(stringRedisTemplate);
        }
        return captchaService;

    }

}
