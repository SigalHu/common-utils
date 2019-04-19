package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.BiConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedBiConsumer<T, U> {

    void accept(T t, U u) throws Exception;

    default UncheckedBiConsumer<T, U> andThen(UncheckedBiConsumer<? super T, ? super U> after) {
        Objects.requireNonNull(after);

        return (l, r) -> {
            accept(l, r);
            after.accept(l, r);
        };
    }
}
