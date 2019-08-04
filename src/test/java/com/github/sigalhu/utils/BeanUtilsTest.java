package com.github.sigalhu.utils;

import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-07-25
 */
public class BeanUtilsTest {

    private static long count = 100000000L;
    private static Person person = new Person();

    private static Map<String, Function<Object, Object>> getterMap = BeanUtils.getters(Person.class);
    private static Map<String, BiConsumer<Object, Object>> setterMap = BeanUtils.setters(Person.class);

    @BeforeClass
    public static void resetPerson() {
        person.setId(1L);
        person.setName("person");
        person.setAge(2);
        person.setHeight(3D);
        person.setWeight(4D);
        person.setPhone("12345679800");
    }

    private static Student generateStudent() {
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
                new Subject("geography", 97)
        ));
        return student;
    }

    @Test
    public void collectFieldValue() {
        Set<Object> results = BeanUtils.collectFieldValue(generateStudent(), "transcript", "score");
        System.out.println(results);
    }

    @Test
    public void testCost() {
        long total = 0L;
        for (int i = 0; i < 10; i++) {
            total += createFunction();
        }
        System.out.println("====== total cost ======");
        System.out.println("平均耗时：" + total / 10D + "ms");
    }

    private static long invokeGetter() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== invoke getter ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += person.getId();
            result += person.getName().hashCode();
            result += person.getAge();
            result += person.getHeight().longValue();
            result += person.getWeight().longValue();
            result += person.getPhone().hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static long createFunction() {
        long result = ThreadLocalRandom.current().nextLong();
        System.out.println("====== create function ======");
        Function<Object, Object> idFunction = getterMap.get("id");
        Function<Object, Object> nameFunction = getterMap.get("name");
        Function<Object, Object> ageFunction = getterMap.get("age");
        Function<Object, Object> heightFunction = getterMap.get("height");
        Function<Object, Object> weightFunction = getterMap.get("weight");
        Function<Object, Object> phoneFunction = getterMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            result += (Long) idFunction.apply(person);
            result += nameFunction.apply(person).hashCode();
            result += (Integer) ageFunction.apply(person);
            result += ((Double) heightFunction.apply(person)).longValue();
            result += ((Double) weightFunction.apply(person)).longValue();
            result += phoneFunction.apply(person).hashCode();
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println("result=" + result);
        return cost;
    }

    private static long invokeSetter() {
        System.out.println("====== invoke setter ======");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            person.setId((long) i);
            person.setName("" + i);
            person.setAge(i);
            person.setHeight((double) i);
            person.setWeight((double) i);
            person.setPhone("" + i);
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println(person);
        return cost;
    }

    private static long createConsumer() {
        System.out.println("====== create consumer ======");
        BiConsumer<Object, Object> idConsumer = setterMap.get("id");
        BiConsumer<Object, Object> nameConsumer = setterMap.get("name");
        BiConsumer<Object, Object> ageConsumer = setterMap.get("age");
        BiConsumer<Object, Object> heightConsumer = setterMap.get("height");
        BiConsumer<Object, Object> weightConsumer = setterMap.get("weight");
        BiConsumer<Object, Object> phoneConsumer = setterMap.get("phone");
        long cost = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
            idConsumer.accept(person, (long) i);
            nameConsumer.accept(person, "" + i);
            ageConsumer.accept(person, i);
            heightConsumer.accept(person, (double) i);
            weightConsumer.accept(person, (double) i);
            phoneConsumer.accept(person, "" + i);
        }
        cost = System.currentTimeMillis() - cost;
        System.out.println("耗时：" + cost + "ms");
        System.out.println(person);
        return cost;
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
    }

    @Data
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private Integer score;
    }
}