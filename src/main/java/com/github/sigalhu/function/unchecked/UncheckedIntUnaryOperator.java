package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.IntUnaryOperator
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntUnaryOperator {

    int applyAsInt(int operand) throws Exception;

    default UncheckedIntUnaryOperator compose(UncheckedIntUnaryOperator before) {
        Objects.requireNonNull(before);
        return (int v) -> applyAsInt(before.applyAsInt(v));
    }

    default UncheckedIntUnaryOperator andThen(UncheckedIntUnaryOperator after) {
        Objects.requireNonNull(after);
        return (int t) -> after.applyAsInt(applyAsInt(t));
    }

    static UncheckedIntUnaryOperator identity() {
        return t -> t;
    }
}
