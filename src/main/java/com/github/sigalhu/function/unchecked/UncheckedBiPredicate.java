package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.BiPredicate
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedBiPredicate<T, U> {

    boolean test(T t, U u) throws Exception;

    default UncheckedBiPredicate<T, U> and(UncheckedBiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) && other.test(t, u);
    }

    default UncheckedBiPredicate<T, U> negate() {
        return (T t, U u) -> !test(t, u);
    }

    default UncheckedBiPredicate<T, U> or(UncheckedBiPredicate<? super T, ? super U> other) {
        Objects.requireNonNull(other);
        return (T t, U u) -> test(t, u) || other.test(t, u);
    }
}
