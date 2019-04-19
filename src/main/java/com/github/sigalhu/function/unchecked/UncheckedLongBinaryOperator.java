package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.LongBinaryOperator
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongBinaryOperator {

    long applyAsLong(long left, long right) throws Exception;
}
