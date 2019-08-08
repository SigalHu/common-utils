package com.github.sigalhu.beanfilter;

import com.github.sigalhu.utils.BeanUtilsTest;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.junit.Assert;
import org.junit.Test;

import java.util.Set;

/**
 * @author huxujun
 * @date 2019-08-08
 */
public class BeanFilterTest {

    @Test
    public void test() {
        Assert.assertTrue(Filters.and(
                Filters.eq("name", "student"),
                Filters.contain("transcript", new BeanUtilsTest.Subject("mathematics", 100)),
                Filters.gt("id", 0L),
                Filters.gte("age", 18),
                Filters.lt("height", 1.8D),
                Filters.lte("weight", 65.5D),
                Filters.in("age", 17, 18, 19)
        ).test(generateStudent()));
    }

    private static BeanUtilsTest.Student generateStudent() {
        BeanUtilsTest.Student student = new BeanUtilsTest.Student();
        student.setId(1L);
        student.setName("student");
        student.setAge(18);
        student.setHeight(1.75D);
        student.setWeight(65.5D);
        student.setPhone("01234567");
        student.setTranscript(Sets.newHashSet(
                new BeanUtilsTest.Subject("mathematics", 100),
                new BeanUtilsTest.Subject("physics", 99),
                new BeanUtilsTest.Subject("organism", 98),
                new BeanUtilsTest.Subject("geography", 97)
        ));
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
    public static class Student extends BeanUtilsTest.Person {
        private Set<BeanUtilsTest.Subject> transcript;
    }

    @Data
    @AllArgsConstructor
    public static class Subject {
        private String name;
        private Integer score;
    }
}