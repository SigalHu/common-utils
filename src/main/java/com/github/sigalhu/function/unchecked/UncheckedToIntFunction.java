package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ToIntFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedToIntFunction<T> {

    int applyAsInt(T value) throws Exception;
}
