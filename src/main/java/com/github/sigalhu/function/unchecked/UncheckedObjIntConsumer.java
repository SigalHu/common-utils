package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ObjIntConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedObjIntConsumer<T> {

    void accept(T t, int value) throws Exception;
}
