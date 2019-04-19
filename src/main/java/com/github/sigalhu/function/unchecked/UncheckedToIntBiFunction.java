package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ToIntBiFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedToIntBiFunction<T, U> {

    int applyAsInt(T t, U u) throws Exception;
}
