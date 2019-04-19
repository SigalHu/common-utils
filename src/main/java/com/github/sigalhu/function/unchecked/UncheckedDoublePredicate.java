package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.DoublePredicate
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoublePredicate {

    boolean test(double value) throws Exception;

    default UncheckedDoublePredicate and(UncheckedDoublePredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) && other.test(value);
    }

    default UncheckedDoublePredicate negate() {
        return (value) -> !test(value);
    }

    default UncheckedDoublePredicate or(UncheckedDoublePredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }
}
