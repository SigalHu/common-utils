package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ToDoubleBiFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedToDoubleBiFunction<T, U> {

    double applyAsDouble(T t, U u) throws Exception;
}
