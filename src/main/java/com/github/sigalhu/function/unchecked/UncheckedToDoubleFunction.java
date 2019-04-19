package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ToDoubleFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedToDoubleFunction<T> {

    double applyAsDouble(T value) throws Exception;
}
