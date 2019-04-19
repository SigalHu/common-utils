package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ToLongFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedToLongFunction<T> {

    long applyAsLong(T value) throws Exception;
}
