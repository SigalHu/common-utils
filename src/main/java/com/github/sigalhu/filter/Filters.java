package com.github.sigalhu.filter;

import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.List;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public class Filters {

    public static RelationalFilter and(BaseFilter... filters) {
        return and(Lists.newArrayList(filters));
    }

    public static RelationalFilter and(List<BaseFilter> filters) {
        return new RelationalFilter(FilterOperator.AND, filters);
    }

    public static RelationalFilter or(BaseFilter... filters) {
        return or(Lists.newArrayList(filters));
    }

    public static RelationalFilter or(List<BaseFilter> filters) {
        return new RelationalFilter(FilterOperator.OR, filters);
    }

    public static RelationalFilter not(BaseFilter... filters) {
        return not(Lists.newArrayList(filters));
    }

    public static RelationalFilter not(List<BaseFilter> filters) {
        return new RelationalFilter(FilterOperator.NOT, filters);
    }

    public static LogicalFilter eq(String fieldName, Long value) {
        return new LogicalFilter(FilterOperator.EQ, fieldName, value);
    }

    public static LogicalFilter eq(String fieldName, Double value) {
        return new LogicalFilter(FilterOperator.EQ, fieldName, value);
    }

    public static LogicalFilter eq(String fieldName, String value) {
        return new LogicalFilter(FilterOperator.EQ, fieldName, value);
    }

    public static LogicalFilter eq(String fieldName, Collection value) {
        return new LogicalFilter(FilterOperator.EQ, fieldName, value);
    }

    public static LogicalFilter contain(String fieldName, Long... values) {
        return new LogicalFilter(FilterOperator.CONTAIN, fieldName, values);
    }

    public static LogicalFilter contain(String fieldName, Double... values) {
        return new LogicalFilter(FilterOperator.CONTAIN, fieldName, values);
    }

    public static LogicalFilter contain(String fieldName, String... values) {
        return new LogicalFilter(FilterOperator.CONTAIN, fieldName, values);
    }

    public static LogicalFilter lt(String fieldName, Long value) {
        return new LogicalFilter(FilterOperator.LT, fieldName, value);
    }

    public static LogicalFilter lt(String fieldName, Double value) {
        return new LogicalFilter(FilterOperator.LT, fieldName, value);
    }

    public static LogicalFilter lte(String fieldName, Long value) {
        return new LogicalFilter(FilterOperator.LTE, fieldName, value);
    }

    public static LogicalFilter lte(String fieldName, Double value) {
        return new LogicalFilter(FilterOperator.LTE, fieldName, value);
    }

    public static LogicalFilter gt(String fieldName, Long value) {
        return new LogicalFilter(FilterOperator.GT, fieldName, value);
    }

    public static LogicalFilter gt(String fieldName, Double value) {
        return new LogicalFilter(FilterOperator.GT, fieldName, value);
    }

    public static LogicalFilter gte(String fieldName, Long value) {
        return new LogicalFilter(FilterOperator.GTE, fieldName, value);
    }

    public static LogicalFilter gte(String fieldName, Double value) {
        return new LogicalFilter(FilterOperator.GTE, fieldName, value);
    }

    public static LogicalFilter in(String fieldName, Long... values) {
        return new LogicalFilter(FilterOperator.IN, fieldName, values);
    }

    public static LogicalFilter in(String fieldName, Double... values) {
        return new LogicalFilter(FilterOperator.IN, fieldName, values);
    }

    public static LogicalFilter in(String fieldName, String... values) {
        return new LogicalFilter(FilterOperator.IN, fieldName, values);
    }
}
