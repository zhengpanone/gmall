package com.zp.module.system.repository.oauth2;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.zp.framework.common.util.collection.CollectionUtils;
import com.zp.framework.common.util.json.JsonUtils;
import com.zp.module.system.dal.dataobject.oauth2.OAuth2AccessTokenDO;
import jakarta.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.zp.module.system.constant.RedisKeyConstants.OAUTH2_ACCESS_TOKEN;

/**
 * Author : zhengpanone
 * Date : 2024/8/6 14:19
 * Version : v1.0.0
 * Description:
 * {@link OAuth2AccessTokenDO} 的 RedisDAO
 */
@Repository
public class OAuth2AccessTokenRedisRepository {
    @Resource
    private StringRedisTemplate stringRedisTemplate;


    public OAuth2AccessTokenDO get(String accessToken) {
        String redisKey = formatKey(accessToken);
        return JsonUtils.parseObject(stringRedisTemplate.opsForValue().get(redisKey), OAuth2AccessTokenDO.class);
    }

    public void set(OAuth2AccessTokenDO accessTokenDO) {
        String redisKey = formatKey(accessTokenDO.getAccessToken());
        // 清理多余字段，避免缓存
        accessTokenDO.setUpdater(null).setUpdateTime(null).setCreateTime(null).setCreator(null).setDeleted(null);
        long time = LocalDateTimeUtil.between(LocalDateTime.now(), accessTokenDO.getExpiresTime(), ChronoUnit.SECONDS);
        if (time > 0) {
            stringRedisTemplate.opsForValue().set(redisKey, JsonUtils.toJsonString(accessTokenDO), time, TimeUnit.SECONDS);
        }
    }

    public void delete(String accessToken) {
        String redisKey = formatKey(accessToken);
        stringRedisTemplate.delete(redisKey);
    }

    public void deleteList(Collection<String> accessTokens) {
        List<String> redisKeys = CollectionUtils.convertList(accessTokens, OAuth2AccessTokenRedisRepository::formatKey);
        stringRedisTemplate.delete(redisKeys);
    }


    private static String formatKey(String accessToken) {
        return String.format(OAUTH2_ACCESS_TOKEN, accessToken);
    }
}
