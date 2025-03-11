package com.zp.gmall.framework.common.util;

import lombok.extern.slf4j.Slf4j;

/**
 * Author : zhengpanone
 * Date : 2025/3/28 09:13
 * Version : v1.0.0
 * Description: 性能追踪工具类
 */
@Slf4j
public class PerfTracker {

    private final long startTime;

    private final String methodName;


    private PerfTracker(String methodName) {
        this.startTime = System.currentTimeMillis();
        this.methodName = methodName;
    }

    public static TimerContext start() {
        return new TimerContext(Thread.currentThread().getStackTrace()[2].getMethodName());
    }

    public static class TimerContext implements AutoCloseable {
        private final PerfTracker tracker;

        public TimerContext(String methodName) {
            this.tracker = new PerfTracker(methodName);
        }


        @Override
        public void close() {
            long executionTime = System.currentTimeMillis() - tracker.startTime;
            if (executionTime > 1000) {
                log.warn("慢查询告警：方法{} 耗时 {} ms", tracker.methodName, executionTime);
            }

        }
    }

/*    public static void main(String[] args) {
        // ✅ 推荐：使用 Try-with-resources 自动计时
        public List<User> listUsersWithPerfTrack(QueryWrapper<User> wrapper) {
            try (PerfTracker.TimerContext ignored = PerfTracker.start()) {
                return userMapper.selectList(wrapper);
            }
        }
    }*/
}

