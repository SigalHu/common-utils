package com.github.sigalhu.timer.cost;

import java.io.Closeable;
import java.util.function.Supplier;

/**
 * 计时器，支持子节点计时
 *
 * @author huxujun
 * @date 2019-08-15
 */
public interface CostTimer extends Closeable {

    /**
     * 开始计时
     */
    void start();

    /**
     * 结束计时
     */
    void end();

    /**
     * 计算耗时
     *
     * @param supplier
     * @param <T>
     * @return
     */
    default <T> T cost(Supplier<T> supplier) {
        try {
            start();
            return supplier.get();
        } finally {
            end();
        }
    }

    /**
     * 计算耗时
     *
     * @param runnable
     */
    default void cost(Runnable runnable) {
        try {
            start();
            runnable.run();
        } finally {
            end();
        }
    }

    /**
     * 获取耗时，单位ms
     *
     * @return
     */
    Long getCost();

    /**
     * 获取子计时器
     *
     * @param name 子计时器名
     * @return
     */
    CostTimer childCostTimer(String name);
}
