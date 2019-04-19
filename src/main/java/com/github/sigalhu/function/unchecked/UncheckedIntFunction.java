package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.IntFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntFunction<R> {

    R apply(int value) throws Exception;
}
