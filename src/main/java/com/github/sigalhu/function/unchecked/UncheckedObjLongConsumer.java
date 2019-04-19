package com.github.sigalhu.function.unchecked;

/**
 * @see java.util.function.ObjLongConsumer
 * @author huxujun
 * @date 2019-04-20
 */
@FunctionalInterface
public interface UncheckedObjLongConsumer<T> {

    void accept(T t, long value) throws Exception;
}
