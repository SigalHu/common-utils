package com.github.sigalhu.utils;

import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2019-05-08
 */
public class ThreadPoolMonitorTest {

    @Test
    public void monitorThreadPool() throws Exception {
        for (int i = 0; i < 20; i++) {
            ThreadPoolMonitor.monitorThreadPool(
                    new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1)),
                    "test-thread-pool-" + i, 1, TimeUnit.SECONDS);
        }
        new CountDownLatch(1).await();
    }
}