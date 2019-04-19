package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.LongToDoubleFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongToDoubleFunction {

    double applyAsDouble(long value) throws Exception;
}
