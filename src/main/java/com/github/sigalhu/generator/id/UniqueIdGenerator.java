package com.github.sigalhu.generator.id;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-04-22
 */
public class UniqueIdGenerator extends BaseIdGenerator {

    /**
     * 进程id递增
     */
    private static final String NEXT_PID_KEY = "id_generator:weak_unique_id:next_pid";

    /**
     * 毫秒占41位，预计可使用69年
     */
    private static final int MILLIS_BITS = 41;
    private static final long MILLIS_MAX = 0x1FFFFFFFFFFL;

    /**
     * 实例id占6位，分布式系统该实例数不超过64时，实例id唯一
     */
    private static final int PID_BITS = 6;
    private static final long PID_MAX = 0x3FL;

    /**
     * 线程id占8位，cpu数不超过256时，线程id在同一ms唯一
     */
    private static final int TID_BITS = 8;
    private static final long TID_MAX = 0xFFL;

    /**
     * 自增数在低8位
     */
    private static final int NUM_BITS = 8;
    private static final long NUM_MAX = 0xFFL;

    /**
     * 为每个线程分配一个id，最多支持256个线程不重复
     */
    private static final AtomicLong NEXT_TID = new AtomicLong(0);
    private static final int[] TID_SEQ = new int[(int) TID_MAX + 1];
    private static final AtomicLong[] NUM_SEQ = new AtomicLong[(int) TID_MAX + 1];

    static {
        for (int tid = 0; tid <= TID_MAX; tid++) {
            TID_SEQ[tid] = tid;
        }
        int idx, tmp;
        for (int bound = (int) TID_MAX + 1; bound > 1; bound--) {
            idx = ThreadLocalRandom.current().nextInt(bound);
            if (idx == bound - 1) {
                continue;
            }
            tmp = TID_SEQ[bound - 1];
            TID_SEQ[bound - 1] = TID_SEQ[idx];
            TID_SEQ[idx] = tmp;
        }

        Arrays.fill(NUM_SEQ, new AtomicLong(0));
    }

    private final long PID;
    private volatile long current = System.currentTimeMillis();

    public UniqueIdGenerator(Function<String, Long> incrOperate) {
        PID = (incrOperate.apply(NEXT_PID_KEY) & PID_MAX) << (NUM_BITS + TID_BITS);
    }

    public UniqueIdGenerator(Function<String, Long> incrOperate, String appName) {
        PID = (incrOperate.apply(NEXT_PID_KEY + ":" + appName) & PID_MAX) << (NUM_BITS + TID_BITS);
    }

    /**
     * 生成非递增id
     *
     * 由于线程的时间片在ms级别，除非位于同一实例id同一ms内有256个以上的线程在不同cpu同时执行nextId生成的id才会重复，
     * 但这只会在cpu数量大于256时才可能出现，另外由于最先获取时间戳，这样的话跨时间片的执行与一般执行nextId生成的id也必定不同
     *
     * @return
     */
    @Override
    public long nextId() {
        long ms, idx, tid, num, c;
        for (;;) {
            if ((ms = System.currentTimeMillis()) > (c = current)) {
                synchronized (this) {
                    if (c == current) {
                        current = ms;
                        Arrays.fill(NUM_SEQ, new AtomicLong(0));
                    }
                }
            }
            ms = ((current - TIMESTAMP_START) & MILLIS_MAX) << (NUM_BITS + TID_BITS + PID_BITS);
            idx = NEXT_TID.getAndIncrement() & TID_MAX;
            tid = TID_SEQ[(int) idx] << NUM_BITS;
            if ((num = NUM_SEQ[(int) idx].getAndIncrement()) > NUM_MAX) {
                continue;
            }
            return ms | PID | tid | num;
        }
    }
}
