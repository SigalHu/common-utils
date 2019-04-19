package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ToLongBiFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedToLongBiFunction<T, U> {

    long applyAsLong(T t, U u) throws Exception;
}
