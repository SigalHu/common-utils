package com.github.sigalhu.setting;

import com.alibaba.fastjson.JSON;
import com.github.sigalhu.setting.annotations.SettingConfiguration;
import com.github.sigalhu.setting.annotations.SettingField;
import lombok.Data;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        Person person = solver.parse(settings, new Person());
        System.err.println(JSON.toJSONString(person));
        person = solver.parse(settings, new Person());
        System.err.println(JSON.toJSONString(person));
    }

    @Data
    @SettingConfiguration(prefix = "bean.test.")
    public static class Person {
        @SettingField(value = "id")
        private long id = 1L;
        @SettingField("name")
        private String name;
        private Integer age;
        private Double height;
        private Double weight;
        private String phone;
    }
}