package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.IntPredicate
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntPredicate {

    boolean test(int value) throws Exception;

    default UncheckedIntPredicate and(UncheckedIntPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) && other.test(value);
    }

    default UncheckedIntPredicate negate() {
        return (value) -> !test(value);
    }

    default UncheckedIntPredicate or(UncheckedIntPredicate other) {
        Objects.requireNonNull(other);
        return (value) -> test(value) || other.test(value);
    }
}
