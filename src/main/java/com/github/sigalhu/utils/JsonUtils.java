package com.github.sigalhu.utils;

import com.alibaba.fastjson.*;
import com.alibaba.fastjson.util.ParameterizedTypeImpl;
import com.github.sigalhu.utils.fastjson.GenericTypeNode;
import com.github.sigalhu.utils.fastjson.JSONPathField;
import com.github.sigalhu.utils.fastjson.JsonPathInfo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.reflections.ReflectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author huxujun
 * @date 2019-05-20
 */
public class JsonUtils {

    private static Map<Type, List<JsonPathInfo>> jsonPathInfoMap = new ConcurrentHashMap<>();

    /**
     * JSON 字符串反序列化为指定类型，支持 {@link JSONPathField}
     *
     * @param text
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseJsonPath(String text, Class<T> clazz) {
        Object object = JSON.parse(text);
        if (object instanceof JSONObject) {
            JSONObject jsonObject = (JSONObject) object;
            completeJsonObject(jsonObject, clazz);
            return jsonObject.toJavaObject(clazz);
        }
        return JSON.parseObject(text, clazz);
    }

    private static void completeJsonObject(JSONObject object, Type type) {
        if (Objects.isNull(object)) {
            return;
        }
        for (JsonPathInfo info : getJsonPathInfos(type)) {
            Object value = null;
            if (StringUtils.isNotBlank(info.getJsonPath())) {
                value = JSONPath.eval(object, info.getJsonPath());
                object.put(info.getFieldName(), value);
            }
            if (Objects.isNull(value)) {
                value = object.get(info.getFieldName());
            }
            if (value instanceof JSONObject) {
                completeJsonObject((JSONObject) value, info.getFieldType());
            }
        }
    }

    private static List<JsonPathInfo> getJsonPathInfos(Type type) {
        return jsonPathInfoMap.computeIfAbsent(type, JsonUtils::buildJsonPathInfos);
    }

    private static List<JsonPathInfo> buildJsonPathInfos(Type type) {
        List<JsonPathInfo> jsonPathInfos = new ArrayList<>();
        for (Field field : ReflectionUtils.getAllFields((Class<?>) type)) {
            field.setAccessible(true);
            JsonPathInfo jsonPathInfo = buildJsonPathInfo(field);
            jsonPathInfos.add(jsonPathInfo);
        }
        return jsonPathInfos;
    }

    private static JsonPathInfo buildJsonPathInfo(Field field) {
        Method writer = null;
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), field.getDeclaringClass());
            writer = descriptor.getWriteMethod();
        } catch (Exception ignored) {
        }
        String path = null;
        if (Objects.nonNull(writer)) {
            JSONPathField f = writer.getAnnotation(JSONPathField.class);
            if (Objects.nonNull(f)) {
                path = f.path();
            }
        }
        if (StringUtils.isEmpty(path)) {
            JSONPathField f = field.getAnnotation(JSONPathField.class);
            if (Objects.nonNull(f)) {
                path = f.path();
            }
        }
        JsonPathInfo info = new JsonPathInfo();
        if (StringUtils.isNotBlank(path)) {
            info.setJsonPath(path);
        }
        info.setFieldName(field.getName());
        info.setFieldType(field.getGenericType());
        return info;
    }

    /**
     * 获取 JSON Path 下的值
     *
     * @param jsonObject
     * @param path
     * @param reference
     * @param <T>
     * @return
     */
    public static <T> T parseJsonPath(Object jsonObject, String path, TypeReference<T> reference) {
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
}
