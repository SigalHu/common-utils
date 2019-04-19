package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.BiFunction
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedBiFunction<T, U, R> {

    R apply(T t, U u) throws Exception;

    default <V> UncheckedBiFunction<T, U, V> andThen(UncheckedFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }
}
