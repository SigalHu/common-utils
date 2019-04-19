package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.DoubleBinaryOperator
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedDoubleBinaryOperator {

    double applyAsDouble(double left, double right) throws Exception;
}
