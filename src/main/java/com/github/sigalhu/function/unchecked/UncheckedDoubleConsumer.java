package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.DoubleConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleConsumer {

    void accept(double value) throws Exception;

    default UncheckedDoubleConsumer andThen(UncheckedDoubleConsumer after) {
        Objects.requireNonNull(after);
        return (double t) -> { accept(t); after.accept(t); };
    }
}
