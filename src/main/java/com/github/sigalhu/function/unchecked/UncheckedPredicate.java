package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.Predicate
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedPredicate<T> {

    boolean test(T t) throws Exception;

    default UncheckedPredicate<T> and(UncheckedPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) && other.test(t);
    }

    default UncheckedPredicate<T> negate() {
        return (t) -> !test(t);
    }

    default UncheckedPredicate<T> or(UncheckedPredicate<? super T> other) {
        Objects.requireNonNull(other);
        return (t) -> test(t) || other.test(t);
    }

    static <T> UncheckedPredicate<T> isEqual(Object targetRef) {
        return (null == targetRef)
                ? Objects::isNull
                : targetRef::equals;
    }
}
