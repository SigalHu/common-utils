package com.github.sigalhu.metric;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author huxujun
 * @date 2019/11/1
 */
public class RecentAverageCalculatorTest {

    private RecentAverageCalculator calculator = new RecentAverageCalculator(1);

    @Test
    public void test() {
        Assert.assertEquals(0, calculator.average(), 1e-10);
        Assert.assertEquals(1, calculator.calculate(1), 1e-10);
        Assert.assertEquals(1, calculator.calculate(2), 1e-10);
        Assert.assertEquals(2, calculator.calculate(3), 1e-10);
        calculator.reset(2);
        Assert.assertEquals(2, calculator.calculate(4), 1e-10);
        Assert.assertEquals(3.5, calculator.calculate(5), 1e-10);
        Assert.assertTrue(calculator.over(4));
        Assert.assertTrue(calculator.under(3));
        Assert.assertFalse(calculator.over(4, 1.5));
        Assert.assertFalse(calculator.under(3, 0.5));
        calculator.reset(1);
        Assert.assertEquals(5, calculator.calculate(5), 1e-10);
    }
}