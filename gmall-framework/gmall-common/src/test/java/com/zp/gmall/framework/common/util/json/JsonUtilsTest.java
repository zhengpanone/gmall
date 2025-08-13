package com.zp.gmall.framework.common.util.json;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

/**
 * Author : zhengpanone
 * Date : 2025/5/9 17:26
 * Version : v1.0.0
 * Description: JSON工具类性能测试
 * Mode.Throughput（吞吐量，单位时间内的操作次数）
 * Mode.AverageTime（平均执行时间）
 * Mode.SampleTime（采样时间）
 * Mode.SingleShotTime（单次执行时间）
 */
//吞吐量，单位时间内的操作次数
@BenchmarkMode(Mode.Throughput)
// 输出时间单位：秒（SECONDS）
@OutputTimeUnit(TimeUnit.SECONDS)
// 测试状态：每个线程单独维护一个实例（避免线程间干扰）
@State(Scope.Thread)
// 测试进程数：1（单进程运行）
@Fork(1)
// 预热配置：5 轮预热，每轮 1 秒（让 JVM 优化生效）
@Warmup(iterations = 5, time = 1)
// 正式测试配置：3 轮测试，每轮 1 秒（取平均值）
@Measurement(iterations = 3, time = 1)
class JsonUtilsTest {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserEntity {
        private Long id;
        private String email;
        private BigDecimal price;
    }

    String json = "{\"id\":122345667,\"email\":\"jianzh5@163.com\",\"price\":12.25}";
    UserEntity userEntity = new UserEntity(13345L, "jianzh5@163.com", BigDecimal.valueOf(12.25));


    @Benchmark
    public UserEntity objectMapper2ObjTest() {
        return JsonUtils.parseObject(json, UserEntity.class);
    }

    @Benchmark
    public String string2StringTest() {
        return JsonUtils.toJsonString(userEntity);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(JsonUtilsTest.class.getSimpleName()) // 指定要运行的测试类
                .forks(1) // 使用 1 个进程
                .build();
        new Runner(opt).run(); // 运行基准测试
    }
}