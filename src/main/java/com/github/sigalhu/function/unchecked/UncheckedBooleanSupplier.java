package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.BooleanSupplier
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedBooleanSupplier {

    boolean getAsBoolean() throws Exception;
}
