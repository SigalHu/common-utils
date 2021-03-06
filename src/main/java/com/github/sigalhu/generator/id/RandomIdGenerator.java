package com.github.sigalhu.generator.id;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicLongArray;

/**
 * @author huxujun
 * @date 2019-04-22
 */
public class RandomIdGenerator extends BaseIdGenerator {

    /**
     * 时间戳占41位，预计可使用69年
     */
    private static final int MILLIS_BITS = 41;
    private static final long MILLIS_MAX = 0x1FFFFFFFFFFL;

    /**
     * 线程id占8位
     */
    private static final int TID_BITS = 8;
    private static final long TID_MAX = 0xFFL;

    /**
     * 自增数占14位
     */
    private static final int NUM_BITS = 14;
    private static final long NUM_MAX = 0x3FFFL;

    /**
     * 为每个线程分配一个id，最多支持256个线程不重复
     */
    private static final AtomicLong NEXT_TID = new AtomicLong(0);
    private static final int[] TID_SEQ = new int[(int) TID_MAX + 1];

    /**
     * 为每一个线程id分配一个对应的自增数
     */
    private static final AtomicLong[] NUM_SEQ = new AtomicLong[(int) TID_MAX + 1];

    static {
        // 打乱线程id的排列顺序，尽可能降低不同进程在同一ms获取到相同的线程id
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

    /**
     * id生成的参考时间戳
     */
    private static volatile long current = System.currentTimeMillis();

    /**
     * 1. 保证同一进程生成的id唯一
     * 2. 多进程情况下有极低概率id重复
     *
     * @return
     */
    public static long nextRandomId() {
        long ms, idx, tid, num, c;
        for (;;) {
            if ((ms = System.currentTimeMillis()) > (c = current)) {
                synchronized (RandomIdGenerator.class) {
                    if (c == current) {
                        current = ms;
                        resetNumSeq();
                    }
                    c = current;
                }
            }
            ms = ((c - TIMESTAMP_START) & MILLIS_MAX) << (NUM_BITS + TID_BITS);
            idx = NEXT_TID.getAndIncrement() & TID_MAX;
            tid = TID_SEQ[(int) idx] << NUM_BITS;
            if ((num = NUM_SEQ[(int) idx].getAndIncrement()) > NUM_MAX || c != current) {
                continue;
            }
            return ms | tid | num;
        }
    }

    @Override
    public long nextId() {
        return nextRandomId();
    }

    private static void resetNumSeq() {
        for (AtomicLong num : NUM_SEQ) {
            num.set(0);
        }
    }
}
