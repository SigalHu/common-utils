package com.github.sigalhu.metric;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 最近均值计算器
 *
 * @author huxujun
 * @date 2019/10/31
 */
@ToString
@EqualsAndHashCode
public class RecentAverageCalculator {

    /**
     * 最近均值
     */
    private double average;

    /**
     * 最近均值的计算窗口
     */
    private int limit;

    /**
     * 在当前均值窗口的偏移量
     */
    private int count;

    /**
     * 当前均值
     */
    private double nextAverage;

    /**
     * 是否直接读取当前均值作为最近均值
     */
    private boolean readNext;

    public RecentAverageCalculator(int limit) {
        this.average = 0;
        this.limit = limit;
        this.count = 0;
        this.nextAverage = 0;
        this.readNext = true;
    }

    /**
     * 重置最近均值的计算窗口
     * 
     * @param limit 新的最近均值的计算窗口
     */
    public void reset(int limit) {
        if (this.limit != limit && count >= (this.limit = limit)) {
            promote();
        }
    }

    /**
     * 计算当前均值
     * 
     * @param value 当前值
     * @return 最近均值
     */
    public double calculate(double value) {
        if (count >= this.limit) {
            promote();
        }
        nextAverage = next(value, nextAverage, ++count);
        return average();
    }

    /**
     * 获取最近均值
     *
     * @return 最近均值
     */
    public double average() {
        return readNext ? nextAverage : average;
    }

    /**
     * 判断当前值是否大于最近均值
     *
     * @param value 当前值
     * @return 判断结果
     */
    public boolean over(double value) {
        return over(value, 1);
    }

    /**
     * 判断当前值是否大于最近均值*权重
     *
     * @param value 当前值
     * @param weight 最近均值的权重
     * @return 判断结果
     */
    public boolean over(double value, double weight) {
        return value > average() * weight;
    }

    /**
     * 判断当前值是否小于最近均值
     *
     * @param value 当前值
     * @return 判断结果
     */
    public boolean under(double value) {
        return under(value, 1);
    }

    /**
     * 判断当前值是否小于最近均值*权重
     *
     * @param value 当前值
     * @param weight 最近均值的权重
     * @return 判断结果
     */
    public boolean under(double value, double weight) {
        return value < average() * weight;
    }

    private double next(double value, double average, int count) {
        return (average * (count - 1) + value) / count;
    }

    private void promote() {
        readNext = false;
        count = 0;
        average = nextAverage;
    }
}
