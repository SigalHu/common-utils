package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.LongSupplier
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongSupplier {

    long getAsLong() throws Exception;
}
