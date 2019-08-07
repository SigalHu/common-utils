package com.github.sigalhu.filter;

import lombok.Getter;

/**
 * @author huxujun
 * @date 2019-07-29
 */
@Getter
public abstract class BaseFilter {

    protected FilterOperator operator;

    protected BaseFilter(FilterOperator operator) {
        this.operator = operator;
    }


}
