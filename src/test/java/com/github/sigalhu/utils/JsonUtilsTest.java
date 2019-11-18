package com.github.sigalhu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author huxujun
 * @date 2019-08-18
 */
public class JsonUtilsTest {

    @Test
    public void leftEquals() {
        String left = "{\"height\":1.75,\"id\":1,\"name\":\"student\",\"phone\":\"01234567\",\"transcript\":[{\"name\":\"mathematics\",\"score\":100},{\"name\":\"physics\",\"score\":99},{\"name\":\"organism\",\"score\":98},{\"name\":\"geography\",\"score\":97}],\"weight\":65.5}";
        String right = "{\"age\":18,\"height\":1.75,\"id\":1,\"name\":\"student\",\"phone\":\"01234567\",\"transcript\":[{\"name\":\"mathematics\",\"score\":100},{\"name\":\"physics\",\"score\":99},{\"name\":\"organism\",\"score\":98},{\"name\":\"geography\",\"score\":97}],\"weight\":65.5}";
        JSONObject lo = JSON.parseObject(left);
        JSONObject ro = JSON.parseObject(right);
        assertTrue(JsonUtils.leftEquals(lo, ro));

        JSONPath.set(lo, "$.transcript.score", null);
        JSONPath.set(lo, "$.cost", null);
        assertTrue(JsonUtils.leftEquals(lo, ro));
    }

    @Test
    public void parseJsonPath() {
        String json = "{\"height\":1.75,\"id\":1,\"name\":\"student\",\"phone\":\"01234567\",\"transcript\":[{\"name\":\"mathematics\",\"score\":100},{\"name\":\"physics\",\"score\":99},{\"name\":\"organism\",\"score\":98},{\"name\":\"geography\",\"score\":97}],\"weight\":65.5}";
        JSONObject object = JSON.parseObject(json);
        System.err.println((String) JsonUtils.parseJsonPath(object, "name", String.class));
        System.err.println((Double) JsonUtils.parseJsonPath(object, "height", Double.class));
        System.err.println((Integer) JsonUtils.parseJsonPath(object, "id", Integer.class));
        System.err.println((Integer) JsonUtils.parseJsonPath(object, "phone", Integer.class));
        System.err.println((Object) JsonUtils.parseJsonPath(object, "transcript.score", new TypeReference<Set<Integer>>() {
        }));
        System.err.println((Object) JsonUtils.parseJsonPath(object, "transcript", new TypeReference<Set<Map<String, Object>>>() {
        }));
        System.err.println((Object) JsonUtils.parseJsonPath(object, "transcript.score", JsonUtils.buildGenericType(Set.class, Integer.class)));
        System.err.println((Object) JsonUtils.parseJsonPath(object, "transcript", JsonUtils.buildGenericType(
                new JsonUtils.GenericTypeNode(Set.class,
                        new JsonUtils.GenericTypeNode(Map.class,
                                new JsonUtils.GenericTypeNode(String.class),
                                new JsonUtils.GenericTypeNode(Object.class))))));
    }
}