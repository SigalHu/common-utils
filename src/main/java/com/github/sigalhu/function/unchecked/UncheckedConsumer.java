package com.github.sigalhu.function.unchecked;

import java.util.Objects;

/**
 * @see java.util.function.Consumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedConsumer<T> {

    void accept(T t) throws Exception;

    default UncheckedConsumer<T> andThen(UncheckedConsumer<? super T> after) {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
