package com.zp.gmall.module.infra.controller.admin.redis;

import com.zp.gmall.framework.common.domain.vo.Result;
import com.zp.gmall.module.infra.controller.admin.redis.vo.RedisMonitorRespVO;
import com.zp.gmall.module.infra.convert.redis.RedisConvert;
import jakarta.annotation.Resource;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.redis.connection.RedisServerCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

import static com.zp.gmall.framework.common.domain.vo.Result.ok;

@Tag(name = "管理后台 - Redis 监控")
@RequestMapping("/infra/redis")
@RestController
public class RedisController {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @GetMapping("/get-monitor-info")
    @Operation(summary = "获得 Redis 监控信息")
//    @PreAuthorize("@ss.hasPermission('infra:redis:get-monitor-info')")
    public Result<RedisMonitorRespVO> getRedisMonitorInfo() {
        // 获得 Redis 统计信息
        Properties info = stringRedisTemplate.execute((RedisCallback<Properties>) RedisServerCommands::info);
        Long dbSize = stringRedisTemplate.execute(RedisServerCommands::dbSize);
        Properties commandStats = stringRedisTemplate.execute((
                RedisCallback<Properties>) connection -> connection.serverCommands().info("commandstats"));
        assert commandStats != null; // 断言，避免警告
        // 拼接结果返回
        return ok(RedisConvert.INSTANCE.build(info, dbSize, commandStats));
    }
}
