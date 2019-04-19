package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.IntToLongFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntToLongFunction {

    long applyAsLong(int value) throws Exception;
}
