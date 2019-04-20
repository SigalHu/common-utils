package com.github.sigalhu.utils;

import com.github.sigalhu.function.unchecked.UncheckedFunction;
import com.github.sigalhu.function.unchecked.UncheckedIntConsumer;
import com.github.sigalhu.function.unchecked.UncheckedIntFunction;
import com.github.sigalhu.function.unchecked.UncheckedIntUnaryOperator;
import org.junit.Assert;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author huxujun
 * @date 2019-04-20
 */
public class TryTest {

    public static String getName(Integer id) throws Exception {
        return "Name_" + id;
    }

    public static void printValue(Integer value) throws Exception {
        System.out.println("The value is " + value);
    }

    @Test
    public void of() {
        String[] names = IntStream.of(1, 2, 3)
                .peek(Try.of((UncheckedIntConsumer) TryTest::printValue))
                .mapToObj(Try.of((UncheckedIntFunction<String>) TryTest::getName))
                .toArray(String[]::new);
        Assert.assertArrayEquals(new String[]{"Name_1", "Name_2", "Name_3"}, names);

        Try.of((UncheckedIntConsumer) TryTest::printValue).accept(11);
        Assert.assertEquals("Name_12", Try.of((UncheckedIntFunction<String>) TryTest::getName).apply(12));
        Assert.assertEquals("Name_13", Try.of((UncheckedFunction<Integer, String>) TryTest::getName).apply(13));
    }
}