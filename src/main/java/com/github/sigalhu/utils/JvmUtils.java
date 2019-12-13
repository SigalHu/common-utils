package com.github.sigalhu.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    /**
     * 获取 Old GC 总次数
     *
     * @return GC 总次数
     */
    public static long getOldGcCount() {
        long gcCount = 0;
        List<GarbageCollectorMXBean> gcs = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean gc : gcs) {
            String name = gc.getName();
            if ("PS MarkSweep".equals(name)
                    || "MarkSweepCompact".equals(name)
                    || "ConcurrentMarkSweep".equals(name)
                    || "G1 Mixed Generation".equals(name)) {
                gcCount += gc.getCollectionCount();
            }
        }
        return gcCount;
    }

    /**
     * 主动触发 Old GC
     *
     * @return 是否触发成功
     */
    public static boolean triggerOldGc() {
        long heapSize = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage().getMax() / 1024 / 1024;
        int largeObjSize = 16, lastObjSize;
        long start = getOldGcCount();
        long end = start + 1;
        for (long cc = start, ii = 1; cc < end; cc = getOldGcCount(), ii++) {
            try {
                byte[][] bytes = new byte[largeObjSize][1024 * 1024];
            } catch (OutOfMemoryError error) {
                return false;
            }
            if (ii * largeObjSize > heapSize) {
                if ((lastObjSize = largeObjSize << 1) <= largeObjSize) {
                    return false;
                }
                largeObjSize = lastObjSize;
                ii = 0;
            }
        }
        return true;
    }

    /**
     * 通过 jmap 触发 Full GC
     *
     * @param timeout 超时时间，单位为 ms
     */
    public static void jmapForGc(int timeout) {
        String gcShell = String.format("jmap -histo:live %s", getProcessId());
        try {
            Process process = Runtime.getRuntime().exec(gcShell);
            try (
                    InputStreamReader outStream = new InputStreamReader(process.getInputStream());
                    BufferedReader outReader = new BufferedReader(outStream);
                    InputStreamReader errStream = new InputStreamReader(process.getErrorStream());
                    BufferedReader errReader = new BufferedReader(errStream)
            ) {
                String line;
                while ((line = outReader.readLine()) != null) {
                }
                while ((line = errReader.readLine()) != null) {
                }
            }
            if (!process.waitFor(timeout, TimeUnit.SECONDS)) {
                process.destroy();
                throw new IllegalStateException("Timeout to wait for executing " + gcShell);
            }
        } catch (IOException | InterruptedException ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 获取进程 id
     *
     * @return
     */
    public static String getProcessId() {
        try {
            // likely works on most platforms
            return ManagementFactory.getRuntimeMXBean().getName().split("@")[0];
        } catch (Exception ex) {
            try {
                // try a Linux-specific way
                return new File("/proc/self").getCanonicalFile().getName();
            } catch (IOException ignoredUseDefault) {
                // Ignore exception.
            }
            throw new IllegalStateException(ex);
        }
    }
}
