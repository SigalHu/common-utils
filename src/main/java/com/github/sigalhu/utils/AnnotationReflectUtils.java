package com.github.sigalhu.utils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @author huxujun
 * @date 2019-05-08
 */
public class AnnotationReflectUtils {

    /**
     * 修改注解的属性
     *
     * @param annotation 注解实例
     * @param attributeName 对应注解的属性
     * @param attributeValue 要修改的属性值
     */
    public static void updateAttribute(Annotation annotation, String attributeName, Object attributeValue) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
            declaredField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, Object> memberValues = (Map<String, Object>) declaredField.get(invocationHandler);
            Assert.isTrue(memberValues.containsKey(attributeName),
                    String.format("The attribute %s is not exist in the %s!", attributeName, annotation.annotationType().getName()));
            memberValues.put(attributeName, attributeValue);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 批量修改注解的属性
     *
     * @param annotation 注解实例
     * @param attributes key为对应注解的属性，value为要修改的属性值
     * @throws IllegalStateException 属性不存在时抛出该异常
     */
    public static void updateAttributes(Annotation annotation, Map<String, Object> attributes) {
        updateAttributes(annotation, attributes, false);
    }

    /**
     * 批量修改注解的属性
     *
     * @param annotation 注解实例
     * @param attributes key为对应注解的属性，value为要修改的属性值
     * @param ignoreNotExists 当注解中不存在key指定的属性时，是否抛出异常
     */
    public static void updateAttributes(Annotation annotation, Map<String, Object> attributes, boolean ignoreNotExists) {
        try {
            InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
            Field declaredField = invocationHandler.getClass().getDeclaredField("memberValues");
            declaredField.setAccessible(true);

            @SuppressWarnings("unchecked")
            Map<String, Object> memberValues = (Map<String, Object>) declaredField.get(invocationHandler);
            attributes.forEach((name, value)->{
                if (memberValues.containsKey(name)) {
                    memberValues.put(name, value);
                } else if (!ignoreNotExists) {
                    throw new IllegalStateException(String.format("The attribute %s is not exist in the %s!", name, annotation.annotationType().getName()));
                }
            });
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }
}
