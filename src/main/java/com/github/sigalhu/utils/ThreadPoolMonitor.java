package com.github.sigalhu.utils;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huxujun
 * @date 2019-05-08
 */
@Slf4j
public class ThreadPoolMonitor {

    private static final ScheduledThreadPoolExecutor EXECUTOR = new ScheduledThreadPoolExecutor(
            4, new ThreadFactoryBuilder().setNameFormat("thread-pool-monitor-%s").build());

    static {
        monitorThreadPool(EXECUTOR, "thread-pool-monitor-scheduled-executor", 10, TimeUnit.SECONDS);
    }

    /**
     * 打印传入线程池的状态日志
     *
     * @param executor
     * @param threadPoolName
     * @param delay
     * @param unit
     */
    public static void monitorThreadPool(ThreadPoolExecutor executor, String threadPoolName, long delay, TimeUnit unit) {
        EXECUTOR.scheduleWithFixedDelay(() -> {
            log.info("threadPoolName={}, poolSize={}, corePoolSize={}, maxPoolSize={}, activeCount={}, taskCount={}, queueSize={}",
                    threadPoolName, executor.getPoolSize(), executor.getCorePoolSize(), executor.getMaximumPoolSize(),
                    executor.getActiveCount(), executor.getTaskCount(), executor.getQueue().size());
        }, 0, delay, unit);
    }
}
