package com.github.sigalhu.beanfilter;

import java.util.Arrays;
import java.util.List;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public class Filters {

    public static RelationalFilter and(BaseFilter... operators) {
        return and(Arrays.asList(operators));
    }

    public static RelationalFilter and(List<BaseFilter> operators) {
        return new RelationalFilter(FilterType.AND, operators);
    }

    public static RelationalFilter or(BaseFilter... operators) {
        return or(Arrays.asList(operators));
    }

    public static RelationalFilter or(List<BaseFilter> operators) {
        return new RelationalFilter(FilterType.OR, operators);
    }

    public static RelationalFilter not(BaseFilter... operators) {
        return not(Arrays.asList(operators));
    }

    public static RelationalFilter not(List<BaseFilter> operators) {
        return new RelationalFilter(FilterType.NOT, operators);
    }

    public static LogicalFilter eq(String fieldName, Object value) {
        return new LogicalFilter(FilterType.EQ, fieldName, value);
    }

    public static LogicalFilter contain(String fieldName, Object values) {
        return new LogicalFilter(FilterType.CONTAIN, fieldName, values);
    }

    public static LogicalFilter lt(String fieldName, Comparable value) {
        return new LogicalFilter(FilterType.LT, fieldName, value);
    }

    public static LogicalFilter lte(String fieldName, Comparable value) {
        return new LogicalFilter(FilterType.LTE, fieldName, value);
    }

    public static LogicalFilter gt(String fieldName, Comparable value) {
        return new LogicalFilter(FilterType.GT, fieldName, value);
    }

    public static LogicalFilter gte(String fieldName, Comparable value) {
        return new LogicalFilter(FilterType.GTE, fieldName, value);
    }

    public static LogicalFilter in(String fieldName, Object... values) {
        return new LogicalFilter(FilterType.IN, fieldName, Arrays.asList(values));
    }
}
