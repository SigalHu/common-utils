package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.LongToIntFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongToIntFunction {

    int applyAsInt(long value) throws Exception;
}
