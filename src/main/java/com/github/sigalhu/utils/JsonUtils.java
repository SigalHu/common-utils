package com.github.sigalhu.utils;

import com.alibaba.fastjson.JSONPath;

import java.util.Objects;
import java.util.Optional;

/**
 * @author huxujun
 * @date 2019-05-20
 */
public class JsonUtils {

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
}
