package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.IntBinaryOperator
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntBinaryOperator {

    int applyAsInt(int left, int right) throws Exception;
}
