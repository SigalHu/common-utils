package com.github.sigalhu.function.unchecked;

/**
 * @see Runnable
 * @author huxujun
 * @date 2019-08-15
 */
@FunctionalInterface
public interface UncheckedRunnable {

    void run() throws Exception;
}
