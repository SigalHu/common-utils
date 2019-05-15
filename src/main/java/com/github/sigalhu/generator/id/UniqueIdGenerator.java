package com.github.sigalhu.generator.id;

import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-04-22
 */
public class UniqueIdGenerator extends BaseIdGenerator {

    /**
     * 进程id递增
     */
    private static final String NEXT_PID_KEY = "id_generator:unique_id:next_pid";

    /**
     * 毫秒占41位，预计可使用69年
     */
    private static final int MILLIS_BITS = 41;
    private static final long MILLIS_MAX = 0x1FFFFFFFFFFL;

    /**
     * 实例id占6位
     */
    private static final int PID_BITS = 6;
    private static final long PID_MAX = 0x3FL;

    /**
     * 自增数在低16位
     */
    private static final int NUM_BITS = 16;
    private static final long NUM_MAX = 0xFFFFL;

    private final long PID;
    private long nextNum = 0L;

    /**
     * id生成的参考时间戳
     */
    private long current = System.currentTimeMillis();

    public UniqueIdGenerator(Function<String, Long> incrOperate) {
        PID = (incrOperate.apply(NEXT_PID_KEY) & PID_MAX) << NUM_BITS;
    }

    public UniqueIdGenerator(Function<String, Long> incrOperate, String appName) {
        PID = (incrOperate.apply(NEXT_PID_KEY + ":" + appName) & PID_MAX) << NUM_BITS;
    }

    /**
     * 保证实例数不超过64时，生成的id唯一
     *
     * @return
     */
    @Override
    public synchronized long nextId() {
        long ms, num;
        for (;;) {
            if ((ms = System.currentTimeMillis()) > current) {
                current = ms;
                nextNum = 0;
            }
            ms = ((current - TIMESTAMP_START) & MILLIS_MAX) << (NUM_BITS + PID_BITS);
            if ((num = nextNum++) > NUM_MAX) {
                continue;
            }
            return ms | PID | num;
        }
    }
}
