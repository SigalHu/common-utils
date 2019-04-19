package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.DoubleToLongFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleToLongFunction {

    long applyAsLong(double value) throws Exception;
}
