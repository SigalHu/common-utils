package com.github.sigalhu.utils;

import com.alibaba.fastjson.JSONPath;

import java.util.*;

/**
 * @author huxujun
 * @date 2019-05-20
 */
public class JsonUtils {

    /**
     * 获取 JSON Path 下的值
     *
     * @param jsonObject
     * @param path
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> Optional<T> parseJsonPath(Object jsonObject, String path, Class<T> clazz) {
        Object result = JSONPath.eval(jsonObject, path);
        if (Objects.isNull(result)) {
            return Optional.empty();
        }
        if (Long.class.equals(clazz)) {
            return Optional.of(clazz.cast(((Number) result).longValue()));
        } else if (Integer.class.equals(clazz)) {
            return Optional.of(clazz.cast(((Number) result).intValue()));
        } else if (Short.class.equals(clazz)) {
            return Optional.of(clazz.cast(((Number) result).shortValue()));
        } else if (Byte.class.equals(clazz)) {
            return Optional.of(clazz.cast(((Number) result).byteValue()));
        } else if (Double.class.equals(clazz)) {
            return Optional.of(clazz.cast(((Number) result).doubleValue()));
        } else if (Float.class.equals(clazz)) {
            return Optional.of(clazz.cast(((Number) result).floatValue()));
        }
        return Optional.of(clazz.cast(result));
    }

    /**
     * 判断 JSON 对象左值和右值是否相等，只比较左值及嵌套的 JSON 对象包含的属性
     *
     * @param left
     * @param right
     * @return
     */
    @SuppressWarnings("unchecked")
    public static boolean leftEquals(Object left, Object right) {
        if (left == null) {
            return true;
        } else if (left instanceof Map) {
            if (right instanceof Map) {
                try {
                    Map<String, Object> lm = (Map<String, Object>) left;
                    Map<String, Object> rm = (Map<String, Object>) right;
                    for (Map.Entry<String, Object> entry : lm.entrySet()) {
                        if (!rm.containsKey(entry.getKey()) || !leftEquals(entry.getValue(), rm.get(entry.getKey()))) {
                            return false;
                        }
                    }
                    return true;
                } catch (ClassCastException ex) {
                    return left.equals(right);
                }
            }
        } else if (left instanceof Collection) {
            if (right instanceof Collection && ((Collection) left).size() == ((Collection) right).size()) {
                for (Iterator liter = ((Collection) left).iterator(), riter = ((Collection) right).iterator();
                     liter.hasNext() && riter.hasNext(); ) {
                    if (!leftEquals(liter.next(), riter.next())) {
                        return false;
                    }
                }
                return true;
            }
        } else if (left.getClass().isArray()) {
            if (right.getClass().isArray()) {
                return leftEquals(ArrayUtils.asList(left), ArrayUtils.asList(right));
            }
        } else {
            return left.equals(right);
        }
        return false;
    }
}
