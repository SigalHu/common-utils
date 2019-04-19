package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.Function
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedFunction<T, R> {

    R apply(T t) throws Exception;

    default <V> UncheckedFunction<V, R> compose(UncheckedFunction<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> UncheckedFunction<T, V> andThen(UncheckedFunction<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T> UncheckedFunction<T, T> identity() {
        return t -> t;
    }
}
