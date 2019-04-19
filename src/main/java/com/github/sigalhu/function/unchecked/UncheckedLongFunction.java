package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.LongFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongFunction<R> {

    R apply(long value) throws Exception;
}
