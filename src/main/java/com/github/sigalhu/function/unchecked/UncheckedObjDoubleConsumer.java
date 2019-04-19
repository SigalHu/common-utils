package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ObjDoubleConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedObjDoubleConsumer<T> {

    void accept(T t, double value) throws Exception;
}
