package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.IntToDoubleFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntToDoubleFunction {

    double applyAsDouble(int value) throws Exception;
}
