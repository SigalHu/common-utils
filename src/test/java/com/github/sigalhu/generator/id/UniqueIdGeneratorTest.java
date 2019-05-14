package com.github.sigalhu.generator.id;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author huxujun
 * @date 2019-05-14
 */
public class UniqueIdGeneratorTest {

    private AtomicLong pid = new AtomicLong(0);
    private UniqueIdGenerator idGenerator = new UniqueIdGenerator(key -> pid.getAndIncrement());

    private ThreadPoolExecutor executor = new ThreadPoolExecutor(
            100, 100, 0, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(20000), new ThreadPoolExecutor.CallerRunsPolicy());

    @Test
    public void repeatTest() throws Exception {
        for (int ii = 0; ii < 100; ii++) {
            System.out.println("repeat count: " + ii);
            this.nextId();
        }
    }

    @Test
    public void nextId() throws Exception {
        long[] ids = new long[10000];
        AtomicInteger incr = new AtomicInteger(0);
        CountDownLatch latch = new CountDownLatch(1);
        for (int ii = 0; ii < ids.length; ii++) {
            executor.execute(() -> {
                try {
                    latch.await();
                    ids[incr.getAndIncrement()] = idGenerator.nextId();
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                }
            });
        }
        latch.countDown();

        do {
            Thread.sleep(100);
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