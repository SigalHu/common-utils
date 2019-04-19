package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.LongUnaryOperator
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongUnaryOperator {

    long applyAsLong(long operand) throws Exception;

    default UncheckedLongUnaryOperator compose(UncheckedLongUnaryOperator before) {
        Objects.requireNonNull(before);
        return (long v) -> applyAsLong(before.applyAsLong(v));
    }

    default UncheckedLongUnaryOperator andThen(UncheckedLongUnaryOperator after) {
        Objects.requireNonNull(after);
        return (long t) -> after.applyAsLong(applyAsLong(t));
    }

    static UncheckedLongUnaryOperator identity() {
        return t -> t;
    }
}
