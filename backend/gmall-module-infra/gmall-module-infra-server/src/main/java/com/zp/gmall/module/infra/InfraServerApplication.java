package com.zp.gmall.module.infra;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.LoggerContext;
import com.zp.gmall.framework.file.config.GmallFileAutoConfiguration;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Author : zhengpanone
 * Date : 2024/1/9 15:22
 * Version : v1.0.0
 */
@SpringBootApplication
@Import(GmallFileAutoConfiguration.class)
public class InfraServerApplication {

    // 禁用 Nacos 自带的 logback 配置（否则它会在 Nacos Config / Discovery 初始化时各加载一次 nacos-logback12.xml，
    // 与主应用的 LoggerContext 抢 CONFIG_LOG_FILE / NAMING_LOG_FILE / REMOTE_LOG_FILE，报 earlier_fa_collision，
    // 后续还会持续刷 "Attempted to append to non started appender"）。
    // 放在 static 块而不是 main() 里：静态块在类加载时执行，早于任何 Nacos 类的静态初始化，
    // 避免 Nacos 客户端在 main() 之前就被触发而提前加载它自己的日志配置。
    // 等价的启动参数写法：-Dnacos.logging.default.config.enabled=false（建议 IDE 的 VM options 里也加一份双保险）
    static {
        System.setProperty("nacos.logging.default.config.enabled", "false");

        // spring.config.import:nacos: 的配置拉取发生在 Spring Boot 日志系统初始化之前
        // （ConfigDataEnvironmentPostProcessor 的 order 早于 LoggingApplicationListener），
        // 这段时间 logback 还是"无配置"的默认控制台输出，logback-spring.xml 里的
        // com.alibaba.nacos=WARN 尚未生效，所以 Nacos 客户端的 INFO 会抢在 Banner 之前刷屏。
        // 这里先用 logback API 把级别压下去；之后 Boot 会用 logback-spring.xml 重新初始化，以那份配置为准。
        if (LoggerFactory.getILoggerFactory() instanceof LoggerContext loggerContext) {
            loggerContext.getLogger("com.alibaba.nacos").setLevel(Level.WARN);
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(InfraServerApplication.class, args);
    }
}
