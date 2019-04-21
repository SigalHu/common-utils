package com.github.sigalhu.generator.id;

import com.github.sigalhu.utils.Try;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.LongSupplier;

/**
 * @author huxujun
 * @date 2019-04-22
 */
public class WeakUniqueShortIdGenerator extends BaseIdGenerator {

    /**
     * 自增数在低10位，每ms最多生成1024个唯一id
     */
    private static final int INCR_BITS = 10;
    private static final long INCR_MAX = 0x3FFL;

    private Function<String, Long> incrOperate;

    /**
     * 自增数
     */
    private String nextIncrKey = "id_generator:weak_unique_short_id:next_incr";

    public WeakUniqueShortIdGenerator(Function<String, Long> incrOperate) {
        this.incrOperate = incrOperate;
    }

    public WeakUniqueShortIdGenerator(Function<String, Long> incrOperate, String appName) {
        this.incrOperate = incrOperate;
        this.nextIncrKey += ":" + appName;
    }

    /**
     * 生成非递增id
     *
     * 每ms最多生成1024个唯一id
     *
     * @return
     */
    @Override
    public long nextId() {
        long ms = ((System.currentTimeMillis() - TIMESTAMP_START) << INCR_BITS) & Long.MAX_VALUE;
        long incr = Try.of(() -> incrOperate.apply(this.nextIncrKey),
                (LongSupplier) ThreadLocalRandom.current()::nextLong).getAsLong() & INCR_MAX;
        return ms | incr;
    }
}
