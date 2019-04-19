package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.DoubleFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleFunction<R> {

    R apply(double value) throws Exception;
}
