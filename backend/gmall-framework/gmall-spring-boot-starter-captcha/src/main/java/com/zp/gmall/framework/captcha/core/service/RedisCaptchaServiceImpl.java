package com.zp.gmall.framework.captcha.core.service;

import com.xingyuv.captcha.service.CaptchaCacheService;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 * Author : zhengpanone
 * Date : 2023/11/11 18:44
 * Version : v1.0.0
 * Description: 基于Redis实现的存储
 * CaptchaCacheService通过java SPI机制，接入方可自定义RedisCaptchaServiceImpl实现
 * SPI实现方式：src/main/resources/下建立/META-INF/services 目录，新增一个以接口命名的文件
 */
public class RedisCaptchaServiceImpl implements CaptchaCacheService {
    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Override
    public void set(String key, String value, long expiresInSeconds) {
        stringRedisTemplate.opsForValue().set(key, value, expiresInSeconds, TimeUnit.SECONDS);
    }

    @Override
    public boolean exists(String key) {
        return Boolean.TRUE.equals(stringRedisTemplate.hasKey(key));
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public String type() {
        return "redis";
    }

    @Override
    public Long increment(String key, long val) {
        return stringRedisTemplate.opsForValue().increment(key, val);
    }
}
