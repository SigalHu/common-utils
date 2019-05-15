package com.github.sigalhu.generator.id;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.*;
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

    @Ignore
    @Test
    public void repeatTest() throws Exception {
        for (int ii = 0; ii < 10000; ii++) {
            System.out.println("repeat count: " + ii);
            this.nextId();
        }
    }

    @Test
    public void nextId() throws Exception {
        @SuppressWarnings("unchecked")
        Future<Long>[] futures = new Future[10000];
        CountDownLatch latch = new CountDownLatch(1);
        for (int ii = 0; ii < futures.length; ii++) {
            futures[ii] = executor.submit(() -> {
                try {
                    latch.await();
                } catch (InterruptedException e) {
                }
                return idGenerator.nextId();
            });
        }
        latch.countDown();

        for (Future future : futures) {
            future.get();
        }

        Set<Long> idSet = new HashSet<>(futures.length);
        for (int i = 0; i < futures.length; i++) {
            if (idSet.contains(futures[i].get())) {
                for (int j = 0; j < futures.length; j++) {
                    if (Objects.equals(futures[i].get(), futures[j].get())) {
                        System.err.println(j + ": " + futures[j].get());
                        break;
                    }
                }
                System.err.println(i + ": " + futures[i].get());
                continue;
            }
            idSet.add(futures[i].get());
        }

        Assert.assertEquals(futures.length, Arrays.stream(futures).mapToLong(future -> {
            try {
                return future.get();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }).distinct().count());
    }
}