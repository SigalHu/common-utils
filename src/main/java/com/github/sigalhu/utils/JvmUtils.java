package com.github.sigalhu.utils;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @author huxujun
 * @date 2019/10/31
 */
public class JvmUtils {

    /**
     * 获取 GC 总次数
     *
     * @return GC 总次数
     */
    public static long getGcCount() {
        long gcCount = 0;
        List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : gcs) {
            gcCount += gc.getCollectionCount();
        }
        return gcCount;
    }

    /**
     * 主动触发 GC
     *
     * @param count GC 最少触发次数
     */
    public static void triggerGc(long count) {
        long start = getGcCount();
        long end = start + count;
        for (long cc = start; cc < end; cc = getGcCount()) {
            byte[] bytes = new byte[1024];
        }
    }
}
