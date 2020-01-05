package com.github.sigalhu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.github.sigalhu.utils.fastjson.GenericTypeNode;
import com.github.sigalhu.utils.fastjson.JSONPathField;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * @author huxujun
 * @date 2019-08-18
 */
public class JsonUtilsTest {

    @Test
    public void parseObject() {
        Student student = generateStudent(2);
        String json = JSON.toJSONString(student);
        System.err.println(json);

        TargetStudent targetStudent = JsonUtils.parseJsonPath(json, TargetStudent.class);
        System.err.println(JSON.toJSONString(targetStudent));
    }

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
        System.err.println(JsonUtils.parseJsonPath(object, "transcript.score", new TypeReference<Set<Integer>>() {
        }));
        System.err.println(JsonUtils.parseJsonPath(object, "transcript", new TypeReference<Set<Map<String, Object>>>() {
        }));
        System.err.println((Object) JsonUtils.parseJsonPath(object, "transcript.score", JsonUtils.buildGenericType(Set.class, Integer.class)));
        System.err.println((Object) JsonUtils.parseJsonPath(object, "transcript", JsonUtils.buildGenericType(
                new GenericTypeNode(Set.class,
                        new GenericTypeNode(Map.class,
                                new GenericTypeNode(String.class),
                                new GenericTypeNode(Object.class))))));
    }

    @Data
    public static class TargetStudent {

        @JSONPathField(path = "$.transcript.name")
        private List<String> subjects;

        @JSONPathField(path = "transcript.score")
        private Set<Integer> scores;

        @JSONPathField(path = "transcript.score")
        private Set<String> strScores;

        private String[] strings;

        @JSONField(deserialize = false)
        private List<Map<String, Object>> transcript;

        private TargetStudent next;
    }

    private static Student generateStudent(int len) {
        if (len == 0) {
            return null;
        }
        Student student = new Student();
        student.setId(1L);
        student.setName("student");
        student.setAge(18);
        student.setHeight(1.75D);
        student.setWeight(65.5D);
        student.setPhone("01234567");
        student.setTranscript(Sets.newHashSet(
                new Subject("mathematics", 100),
                new Subject("physics", 99),
                new Subject("organism", 98),
                new Subject("geography", 98)
        ));
        student.setNext(generateStudent(len - 1));
        return student;
    }

    @Data
    public static class Person {
        private Long id;
        private String name;
        private Integer age;
        private Double height;
        private Double weight;
        private String phone;
    }

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    public static class Student extends Person {
        private Set<Subject> transcript;
        private Student next;
    }

    @Data
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private Integer score;
    }
}