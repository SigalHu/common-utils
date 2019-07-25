package com.github.sigalhu.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-07-24
 */
public class BeanUtils {

    @SuppressWarnings("unchecked")
    public static <T> Map<String, Function<T, Object>> getters(Class<T> clazz) {
        try {
            Map<String, Function<T, Object>> getterMap = new HashMap<>();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodType getter = MethodType.methodType(Function.class);
            MethodType getterType = MethodType.methodType(Object.class, Object.class);
            for (PropertyDescriptor descriptor : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                Method method = descriptor.getReadMethod();
                if ("getClass".equals(method.getName())) {
                    continue;
                }
                MethodHandle handle = lookup.findVirtual(clazz, method.getName(), MethodType.methodType(method.getReturnType()));
                getterMap.put(descriptor.getName(),
                        (Function<T, Object>) LambdaMetafactory.metafactory(
                                lookup, "apply", getter, getterType, handle, handle.type()
                        ).getTarget().invokeExact());
            }
            return getterMap;
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> Map<String, BiConsumer<T, Object>> setters(Class<T> clazz) {
        try {
            Map<String, BiConsumer<T, Object>> setterMap = new HashMap<>();
            MethodHandles.Lookup lookup = MethodHandles.lookup();
            MethodType setter = MethodType.methodType(BiConsumer.class);
            MethodType setterType = MethodType.methodType(void.class, Object.class, Object.class);
            for (PropertyDescriptor descriptor : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                Method method = descriptor.getWriteMethod();
                if (method == null) {
                    continue;
                }
                MethodHandle handle = lookup.findVirtual(clazz, method.getName(), MethodType.methodType(method.getReturnType(), method.getParameterTypes()));
                setterMap.put(descriptor.getName(),
                        (BiConsumer<T, Object>) LambdaMetafactory.metafactory(
                                lookup, "accept", setter, setterType, handle, handle.type()
                        ).getTarget().invokeExact());
            }
            return setterMap;
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }
}
