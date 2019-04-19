package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.LongPredicate
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongPredicate {

    boolean test(long value) throws Exception;

    default UncheckedLongPredicate and(UncheckedLongPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) && other.test(value);
    }

    default UncheckedLongPredicate negate() {
        return (value) -> !test(value);
    }

    default UncheckedLongPredicate or(UncheckedLongPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }
}
