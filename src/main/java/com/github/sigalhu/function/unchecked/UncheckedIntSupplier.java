package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.IntSupplier
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedIntSupplier {

    int getAsInt() throws Exception;
}
