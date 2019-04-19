package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.LongConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedLongConsumer {

    void accept(long value) throws Exception;

    default UncheckedLongConsumer andThen(UncheckedLongConsumer after) {
        Objects.requireNonNull(after);
        return (long t) -> { accept(t); after.accept(t); };
    }
}
