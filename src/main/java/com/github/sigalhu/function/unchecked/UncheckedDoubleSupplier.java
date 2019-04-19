package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.DoubleSupplier
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleSupplier {

    double getAsDouble() throws Exception;
}
