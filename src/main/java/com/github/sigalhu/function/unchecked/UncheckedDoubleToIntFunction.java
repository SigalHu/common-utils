package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.DoubleToIntFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleToIntFunction {

    int applyAsInt(double value) throws Exception;
}
