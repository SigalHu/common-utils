package com.github.sigalhu.generator.id;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huxujun
 * @date 2019-04-22
 */
public class RandomIdGeneratorTest {

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            100, 100, 0, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20000), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void repeatTest() throws Exception {
        for (int ii = 0; ii < 10; ii++) {
            System.out.println("repeat count: " + ii);
            this.nextRandomIdMillis();
        }
    }

    @Test
    public void nextRandomIdMillis() throws Exception {
        long[] ids = new long[10000];
        AtomicInteger incr = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(1);
        for (int ii = 0; ii < ids.length; ii++) {
            executor.execute(() -> {
                try {
                    latch.await();
                    ids[incr.getAndIncrement()] = RandomIdGenerator.nextRandomId();
                    // 假设一个线程在1ms内只获取一次id
//                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
        latch.countDown();

        do {
            Thread.sleep(1000);
        } while (executor.getActiveCount() > 0);

        Set<Long> idSet = new HashSet<>(ids.length);
        for (int i = 0; i < ids.length; i++) {
            if (idSet.contains(ids[i])) {
                System.err.println(i + ": " + ids[i]);
                continue;
            }
            idSet.add(ids[i]);
        }

        Assert.assertEquals(ids.length, Arrays.stream(ids).distinct().count());
    }
}