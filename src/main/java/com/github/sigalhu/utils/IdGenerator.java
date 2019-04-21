package com.github.sigalhu.utils;

import org.apache.commons.lang3.time.DateUtils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Function;
import java.util.function.LongSupplier;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public class IdGenerator {

    /**
     * 时间戳从2019-01-01 08:00:00开始
     */
    private static final long TIMESTAMP_START = Try.of(() ->
            DateUtils.parseDate("2019-01-01 08:00:00", "yyyy-MM-dd HH:mm:ss").getTime()).getAsLong();

    /**
     * 毫秒在高42位，有效位41位，预计可使用69年
     */
    private static final int RANDOM_MILLIS_HIGH_BITS = 42;
    private static final long RANDOM_MILLIS_MAX = 0x1FFFFFFFFFFL;

    /**
     * 线程id在中间8位，单一进程的线程数不超过256时，线程id唯一
     */
    private static final int RANDOM_TID_MIDDLE_BITS = 8;
    private static final long RANDOM_TID_MAX = 0xFFL;

    /**
     * 随机数在低14位
     */
    private static final int RANDOM_NUM_LOW_BITS = 14;
    private static final long RANDOM_NUM_MAX = 0x3FFFL;

    /**
     * 为每个线程分配一个id，最多支持256个线程不重复
     */
    private static final AtomicLong NEXT_TID = new AtomicLong(0);
    private static final int[] TID_SEQ = new int[(int) RANDOM_TID_MAX + 1];

    static {
        for (int tid = 0; tid <= RANDOM_TID_MAX; tid++) {
            TID_SEQ[tid] = tid;
        }
        int idx, tmp;
        for (int bound = (int) RANDOM_TID_MAX + 1; bound > 1; bound--) {
            idx = ThreadLocalRandom.current().nextInt(bound);
            if (idx == bound - 1) {
                continue;
            }
            tmp = TID_SEQ[bound - 1];
            TID_SEQ[bound - 1] = TID_SEQ[idx];
            TID_SEQ[idx] = tmp;
        }
    }

    /**
     * 1. 保证同一进程同一ms最多256个线程生成的id不重复
     * 2. 多进程情况下有极低概率id重复
     *
     * @return
     */
    public static long randomId() {
        long ms = ((System.currentTimeMillis() - TIMESTAMP_START) & RANDOM_MILLIS_MAX)
                << (RANDOM_NUM_LOW_BITS + RANDOM_TID_MIDDLE_BITS);
        long num = NEXT_TID.getAndIncrement() & RANDOM_TID_MAX;
        long t = TID_SEQ[(int) num] << RANDOM_NUM_LOW_BITS;
        long r = ThreadLocalRandom.current().nextLong() & RANDOM_NUM_MAX;
        return ms | r | t;
    }

    /**
     * 进程id递增
     */
    private String nextPidKey = "id_generator:unique_id:next_pid";

    /**
     * 毫秒在高42位，有效位41位，预计可使用69年
     */
    private static final int UNIQUE_MILLIS_HIGH_BITS = 42;
    private static final long UNIQUE_MILLIS_MAX = 0x1FFFFFFFFFFL;

    /**
     * 实例id在中间高6位，分布式系统该实例数不超过64时，实例id唯一
     */
    private static final int UNIQUE_PID_MIDDLE_HIGH_BITS = 6;
    private static final long UNIQUE_PID_MAX = 0x3FL;

    /**
     * 线程id在中间低8位，cpu数不超过256时，线程id在同一ms唯一
     */
    private static final int UNIQUE_TID_MIDDLE_LOW_BITS = 8;
    private static final long UNIQUE_TID_MAX = 0xFFL;

    /**
     * 自增数在低8位
     */
    private static final int UNIQUE_NUM_LOW_BITS = 8;
    private static final long UNIQUE_NUM_MAX = 0xFFL;

    private final long PID;

    public IdGenerator(Function<String, Long> incrOperate) {
        this.incrOperate = incrOperate;
        PID = (incrOperate.apply(this.nextPidKey) & UNIQUE_PID_MAX)
                << (UNIQUE_NUM_LOW_BITS + UNIQUE_TID_MIDDLE_LOW_BITS);
    }

    public IdGenerator(Function<String, Long> incrOperate, String appName) {
        this.incrOperate = incrOperate;
        this.nextPidKey += ":" + appName;
        this.nextIncrKey += ":" + appName;
        PID = (incrOperate.apply(this.nextPidKey) & UNIQUE_PID_MAX)
                << (UNIQUE_NUM_LOW_BITS + UNIQUE_TID_MIDDLE_LOW_BITS);
    }

    /**
     * 生成非递增id
     *
     * 由于线程的时间片在ms级别，除非位于同一实例id同一ms内有256个以上的线程在不同cpu同时执行weakUniqueId生成的id才会重复，
     * 但这只会在cpu数量大于256时才可能出现，另外由于最先获取时间戳，这样的话跨时间片的执行与一般执行weakUniqueId生成的id也必定不同
     *
     * @return
     */
    public long weakUniqueId() {
        long ms = ((System.currentTimeMillis() - TIMESTAMP_START) & UNIQUE_MILLIS_MAX)
                << (UNIQUE_NUM_LOW_BITS + UNIQUE_TID_MIDDLE_LOW_BITS + UNIQUE_PID_MIDDLE_HIGH_BITS);
        long num = NEXT_TID.getAndIncrement() & UNIQUE_TID_MAX;
        long tid = TID_SEQ[(int) num] << UNIQUE_NUM_LOW_BITS;
        return ms | PID | tid | num;
    }

    private Function<String, Long> incrOperate;

    /**
     * 自增数
     */
    private String nextIncrKey = "id_generator:unique_short_id:next_incr";

    /**
     * 自增数在低10位，每ms最多生成1024个唯一id
     */
    private static final int UNIQUE_INCR_SHORT_BITS = 10;
    private static final long UNIQUE_INCR_SHORT_MAX = 0x3FFL;

    /**
     * 生成非递增id
     *
     * 每ms最多生成1024个唯一id
     *
     * @return
     */
    public long weakUniqueShortId() {
        long ms = ((System.currentTimeMillis() - TIMESTAMP_START) << UNIQUE_INCR_SHORT_BITS) & Long.MAX_VALUE;
        long incr = Try.of(() -> incrOperate.apply(this.nextIncrKey),
                (LongSupplier) ThreadLocalRandom.current()::nextLong)
                .getAsLong() & UNIQUE_INCR_SHORT_MAX;
        return ms | incr;
    }
}
