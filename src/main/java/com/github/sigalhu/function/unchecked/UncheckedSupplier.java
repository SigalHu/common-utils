package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.Supplier
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedSupplier<T> {

    T get() throws Exception;
}
