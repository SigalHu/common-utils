package com.github.sigalhu.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-07-24
 */
public class BeanUtils {

    /**
     * 缓存 getter/setter
     */
    private static Map<Class, Map<String, Function<Object, Object>>> getterCache = new ConcurrentHashMap<>();
    private static Map<Class, Map<String, BiConsumer<Object, Object>>> setterCache = new ConcurrentHashMap<>();

    /**
     * 获取 bean 所有字段的 getter
     *
     * @param clazz bean 的 class
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Function<Object, Object>> getters(Class clazz) {
        return getterCache.computeIfAbsent(clazz, c -> {
            try {
                Map<String, Function<Object, Object>> getterMap = new HashMap<>();
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
                            (Function<Object, Object>) LambdaMetafactory.metafactory(
                                    lookup, "apply", getter, getterType, handle, handle.type()
                            ).getTarget().invokeExact());
                }
                return getterMap;
            } catch (Throwable ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    /**
     * 获取 bean 所有字段的 setter
     *
     * @param clazz bean 的 class
     * @return
     */
    @SuppressWarnings("unchecked")
    public static Map<String, BiConsumer<Object, Object>> setters(Class clazz) {
        return setterCache.computeIfAbsent(clazz, c -> {
            try {
                Map<String, BiConsumer<Object, Object>> setterMap = new HashMap<>();
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
                            (BiConsumer<Object, Object>) LambdaMetafactory.metafactory(
                                    lookup, "accept", setter, setterType, handle, handle.type()
                            ).getTarget().invokeExact());
                }
                return setterMap;
            } catch (Throwable ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    /**
     * 获取 bean 指定字段路径值的集合
     *
     * @param entity     实例
     * @param fieldNames 字段路径
     * @return
     */
    public static Set<Object> collectFieldValue(Object entity, String... fieldNames) {
        return collectFieldValue(entity, Arrays.asList(fieldNames));
    }

    public static Set<Object> collectFieldValue(Object entity, List<String> fieldNames) {
        Set<Object> result = new HashSet<>();
        collectFieldValue(entity, fieldNames, result);
        return result;
    }

    private static void collectFieldValue(Object entity, List<String> fieldNames, Set<Object> fieldValues) {
        for (int i = 0; i < fieldNames.size(); i++) {
            if (entity instanceof Collection) {
                for (Object item : (Collection) entity) {
                    collectFieldValue(item, fieldNames.subList(i, fieldNames.size()), fieldValues);
                }
                return;
            } else if (entity instanceof Map) {
                entity = ((Map) entity).get(fieldNames.get(i));
            } else {
                if (entity == null) {
                    return;
                }
                Function<Object, Object> getter = getters(entity.getClass()).get(fieldNames.get(i));
                if (getter == null) {
                    return;
                }
                entity = getter.apply(entity);
            }
        }
        if (entity != null) {
            fieldValues.add(entity);
        }
    }
}
