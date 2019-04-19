package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.DoubleUnaryOperator
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleUnaryOperator {

    double applyAsDouble(double operand) throws Exception;

    default UncheckedDoubleUnaryOperator compose(UncheckedDoubleUnaryOperator before) {
        Objects.requireNonNull(before);
        return (double v) -> applyAsDouble(before.applyAsDouble(v));
    }

    default UncheckedDoubleUnaryOperator andThen(UncheckedDoubleUnaryOperator after) {
        Objects.requireNonNull(after);
        return (double t) -> after.applyAsDouble(applyAsDouble(t));
    }

    static UncheckedDoubleUnaryOperator identity() {
        return t -> t;
    }
}
