package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.IntConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntConsumer {

    void accept(int value) throws Exception;

    default UncheckedIntConsumer andThen(UncheckedIntConsumer after) {
        Objects.requireNonNull(after);
        return (int t) -> { accept(t); after.accept(t); };
    }
}
