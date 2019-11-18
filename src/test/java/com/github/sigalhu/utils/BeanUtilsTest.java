package com.github.sigalhu.utils;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Sets;
import lombok.*;
import org.junit.BeforeClass;
import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Supplier;

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
        student.setLastTrans(new Subject[]{
                new Subject("mathematics", 97),
                new Subject("physics", 98),
                new Subject("organism", 99),
                new Subject("geography", 100)
        });
        student.setScores(new int[]{90, 91, 92, 93});
        return student;
    }

    @Test
    public void collectFieldValue() {
        Set<Object> results = BeanUtils.collectFieldValue(generateStudent(), "scores");
        System.out.println(JSON.toJSONString(results));
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

    @Test
    public void function() throws Exception {
        resetPerson();
        Method getAge = Person.class.getMethod("getAge");
        Method setAge = Person.class.getMethod("setAge", Integer.class);
        Function<Person, Integer> phoneGetter = BeanUtils.function(getAge, Function.class);
        BiConsumer<Person, Integer> phoneSetter = BeanUtils.function(setAge, BiConsumer.class);

        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ) {
            setAge.invoke(person, i + 1);
            i = (int) getAge.invoke(person);
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ) {
            phoneSetter.accept(person, i + 1);
            i = phoneGetter.apply(person);
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ) {
            person.setAge(i + 1);
            i = person.getAge();
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);
    }

    @Test
    public void testStatic() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method getTest = Person.class.getMethod("getTest");
        Supplier<String> testGetter = BeanUtils.function(getTest, Supplier.class);

        long start, end;
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            getTest.invoke(person);
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            testGetter.get();
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);

        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            Person.getTest();
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);
    }

    @Test
    public void testConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor c = Person.class.getConstructor(Integer.class);
        Function<Integer, Person> s = BeanUtils.function(c, Function.class);

        long start, end;
        System.gc();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ) {
            i = ((Person) c.newInstance(i + 1)).getAge();
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);

        System.gc();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ) {
            i = s.apply(i + 1).getAge();
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);

        System.gc();
        start = System.currentTimeMillis();
        for (int i = 0; i < 100000; ) {
            i = new Person(i + 1).getAge();
        }
        end = System.currentTimeMillis();
        System.err.println(end - start);
    }

    @Test
    public void parseGenericClass() {
        System.err.println(BeanUtils.parseGenericClass(G2.class));
        G1<Person, Student> g1 = new G1<>();
        System.err.println(BeanUtils.parseGenericClass(g1.getClass()));
    }

    public static class G1<T, U> {
    }

    public static class G2 extends G1<Person, Student> {
    }

    @Data
    @NoArgsConstructor
    public static class Person {

        public Person(Integer age) {
            this.age = age;
        }

        public static String getTest() {
            return "test";
        }

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
        private Subject[] lastTrans;
        private int[] scores;
    }

    @Data
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private Integer score;
    }
}