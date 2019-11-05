package com.github.sigalhu.setting;

import com.alibaba.fastjson.JSON;
import com.github.sigalhu.setting.annotations.SettingConfiguration;
import com.github.sigalhu.setting.annotations.SettingField;
import lombok.*;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author huxujun
 * @date 2019/11/5
 */
public class BeanSettingSolverTest {

    @Test
    public void parse() {
        Map<String, String> settings = new HashMap<>();
        settings.put("bean.test.id", "100");
        settings.put("bean.test.name", "test");

        BeanSettingSolver solver = new BeanSettingSolver();
        Student student = solver.parse(settings, new Student());
        System.err.println(JSON.toJSONString(student));
    }

    @Data
    @NoArgsConstructor
    public static class Person {
        @SettingField("id")
        private Long id;
        @SettingField("id")
        private String name;
        @SettingField("id")
        private Integer age;
        @SettingField("id")
        private Double height;
        @SettingField("id")
        private Double weight;
        @SettingField("id")
        private String phone;
    }

    @Data
    @ToString(callSuper = true)
    @EqualsAndHashCode(callSuper = true)
    @SettingConfiguration(prefix = "bean.test.")
    public static class Student extends Person {
        private Set<Subject> transcript;
        private Subject[] lastTrans;
        private int[] scores;

        @Override
        @SettingField("id2")
        public void setId(Long id) {
            super.setId(id);
        }
    }

    @Data
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private Integer score;
    }
}