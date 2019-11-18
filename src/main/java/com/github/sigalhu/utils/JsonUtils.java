package com.github.sigalhu.utils;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.lang.reflect.Type;
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
     * @param reference
     * @param <T>
     * @return
     */
    public static <T> T parseJsonPath(Object jsonObject, String path, TypeReference reference) {
        return parseJsonPath(jsonObject, path, reference.getType());
    }

    /**
     * 获取 JSON Path 下的值
     *
     * @param jsonObject
     * @param path
     * @param type
     * @param <T>
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseJsonPath(Object jsonObject, String path, Type type) {
        Object result = JSONPath.eval(jsonObject, path);
        if (Objects.isNull(result)) {
            return null;
        }
        try {
            if (Object.class.equals(type)) {
                return (T) result;
            } else if (result instanceof JSONObject) {
                return ((JSONObject) result).toJavaObject(type);
            } else if (result instanceof JSONArray) {
                return ((JSONArray) result).toJavaObject(type);
            } else if (String.class.equals(type)) {
                return (T) String.valueOf(result);
            } else if (NumberUtils.isPrimitive(type)) {
                return result instanceof Number ? (T) NumberUtils.toPrimitive((Number) result, type) :
                        (T) NumberUtils.toPrimitive(String.valueOf(result), type);
            }
        } catch (Exception ex) {
        }
        return JSON.parseObject(JSON.toJSONString(result), type);
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
                        if (!leftEquals(entry.getValue(), rm.get(entry.getKey()))) {
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

    /**
     * 构建反序列化类型，例如：
     * 通过 {@code buildGenericType(Set.class, List.class, String.class)} 来构建 {@code Set<List<String>>}
     *
     * @param types
     * @return
     */
    public static Type buildGenericType(Type... types) {
        ParameterizedTypeImpl beforeType = null;
        if (types != null && types.length > 0) {
            for (int i = types.length - 1; i > 0; i--) {
                beforeType = new ParameterizedTypeImpl(new Type[]{beforeType == null ? types[i] : beforeType}, null, types[i - 1]);
            }
        }
        return beforeType;
    }

    /**
     * 构建反序列化类型，例如：
     * 通过 {@code buildGenericType(new GenericTypeNode(Map.class, new GenericTypeNode(String), new GenericTypeNode(Long.class)))}
     * 来构建 {@code Map<String, Long>}
     *
     * @param rootNodes
     * @return
     */
    public static Type buildGenericType(GenericTypeNode rootNodes) {
        List<GenericTypeNode> nextNodes = rootNodes.getNextNodes();
        return CollectionUtils.isEmpty(nextNodes) ? rootNodes.getType() :
                new ParameterizedTypeImpl(nextNodes.stream().map(JsonUtils::buildGenericType).toArray(Type[]::new), null, rootNodes.getType());
    }

    @Getter
    @AllArgsConstructor
    public static class GenericTypeNode {
        private Type type;
        private List<GenericTypeNode> nextNodes;

        public GenericTypeNode(Type type, GenericTypeNode... nextNodes) {
            this.type = type;
            if (org.apache.commons.lang3.ArrayUtils.isNotEmpty(nextNodes)) {
                this.nextNodes = Arrays.asList(nextNodes);
            }
        }
    }
}
