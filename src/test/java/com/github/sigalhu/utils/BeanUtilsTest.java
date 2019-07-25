package com.github.sigalhu.utils;

import lombok.Data;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;
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

    private static Map<String, Function<Person, Object>> getterMap = BeanUtils.getters(Person.class);
    private static Map<String, BiConsumer<Person, Object>> setterMap = BeanUtils.setters(Person.class);

    @BeforeClass
    public static void resetPerson() {
        person.setId(1L);
        person.setName("person");
        person.setAge(2);
        person.setHeight(3D);
        person.setWeight(4D);
        person.setPhone("12345679800");
    }

    @Test
    public void testCost() {
        long total = 0L;
        for (int i = 0; i < 10; i++) {
            total += invokeSetter();
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
        Function<Person, Object> idFunction = getterMap.get("id");
        Function<Person, Object> nameFunction = getterMap.get("name");
        Function<Person, Object> ageFunction = getterMap.get("age");
        Function<Person, Object> heightFunction = getterMap.get("height");
        Function<Person, Object> weightFunction = getterMap.get("weight");
        Function<Person, Object> phoneFunction = getterMap.get("phone");
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
        BiConsumer<Person, Object> idConsumer = setterMap.get("id");
        BiConsumer<Person, Object> nameConsumer = setterMap.get("name");
        BiConsumer<Person, Object> ageConsumer = setterMap.get("age");
        BiConsumer<Person, Object> heightConsumer = setterMap.get("height");
        BiConsumer<Person, Object> weightConsumer = setterMap.get("weight");
        BiConsumer<Person, Object> phoneConsumer = setterMap.get("phone");
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
}