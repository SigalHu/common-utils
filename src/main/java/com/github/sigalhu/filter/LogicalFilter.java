package com.github.sigalhu.filter;

import lombok.Getter;

/**
 * @author huxujun
 * @date 2019-07-29
 */
@Getter
public class LogicalFilter extends BaseFilter {

    private String fieldName;
    private Object value;

    LogicalFilter(FilterOperator operator, String fieldName, Object value) {
        super(operator);
        this.fieldName = fieldName;
        this.value = value;
    }
}
