package com.github.sigalhu.utils;

import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.invoke.LambdaMetafactory;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.Constructor;
import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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

    private static final MethodHandles.Lookup lookup = MethodHandles.lookup();

    /**
     * 缓存 {@link FunctionalInterface} 的 {@link Method} 或 {@link Constructor}
     */
    private static Map<Class, Method> invokedMethodCache = new ConcurrentHashMap<>();

    /**
     * 通过 {@link Executable} 生成 {@link FunctionalInterface}
     *
     * @param method
     * @param invokedType
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T function(Executable method, Class invokedType) {
        if (Objects.isNull(method) || Objects.isNull(invokedType)) {
            return null;
        }
        Assert.isTrue(invokedType.isAnnotationPresent(FunctionalInterface.class),
                "The invokedType must be a functional interface!");
        Method invokedMethod = invokedMethodCache.computeIfAbsent(invokedType, type -> {
            for (Method m : invokedType.getMethods()) {
                if (Modifier.isAbstract(m.getModifiers())) {
                    return m;
                }
            }
            throw new IllegalStateException("The invokedType must have an abstract method!");
        });
        try {
            MethodHandle handle;
            if (method instanceof Constructor) {
                Constructor c = (Constructor) method;
                handle = lookup.findConstructor(c.getDeclaringClass(),
                        MethodType.methodType(void.class, c.getParameterTypes()));
            } else {
                Method m = (Method) method;
                if (Modifier.isStatic(m.getModifiers())) {
                    handle = lookup.findStatic(m.getDeclaringClass(), m.getName(),
                            MethodType.methodType(m.getReturnType(), m.getParameterTypes()));
                } else {
                    handle = lookup.findVirtual(m.getDeclaringClass(), m.getName(),
                            MethodType.methodType(m.getReturnType(), m.getParameterTypes()));
                }
            }
            return (T) LambdaMetafactory.metafactory(
                    lookup, invokedMethod.getName(), MethodType.methodType(invokedType),
                    MethodType.methodType(invokedMethod.getReturnType(), invokedMethod.getParameterTypes()),
                    handle, handle.type()
            ).getTarget().invoke();
        } catch (Throwable ex) {
            throw new IllegalStateException(ex);
        }
    }

    /**
     * 获取 bean 所有字段的 getter
     *
     * @param clazz bean 的 class
     * @return
     */
    public static Map<String, Function<Object, Object>> getters(Class clazz) {
        return getterCache.computeIfAbsent(clazz, c -> {
            try {
                Map<String, Function<Object, Object>> getterMap = new HashMap<>();
                for (PropertyDescriptor descriptor : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                    Method method = descriptor.getReadMethod();
                    if ("getClass".equals(method.getName())) {
                        continue;
                    }
                    getterMap.put(descriptor.getName(), function(method, Function.class));
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
    public static Map<String, BiConsumer<Object, Object>> setters(Class clazz) {
        return setterCache.computeIfAbsent(clazz, c -> {
            try {
                Map<String, BiConsumer<Object, Object>> setterMap = new HashMap<>();
                for (PropertyDescriptor descriptor : Introspector.getBeanInfo(clazz).getPropertyDescriptors()) {
                    Method method = descriptor.getWriteMethod();
                    if (method == null) {
                        continue;
                    }
                    setterMap.put(descriptor.getName(), function(method, BiConsumer.class));
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
            if (entity instanceof Collection) {
                fieldValues.addAll((Collection<?>) entity);
            } else if (entity.getClass().isArray()) {
                fieldValues.addAll(ArrayUtils.asList(entity));
            } else {
                fieldValues.add(entity);
            }
        }
    }
}
