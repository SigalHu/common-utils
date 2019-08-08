package com.github.sigalhu.beanfilter;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public class RelationalFilter extends BaseFilter {

    private List<BaseFilter> operators;

    RelationalFilter(FilterType type, List<BaseFilter> operators) {
        super(type);
        this.operators = operators == null ? Lists.newArrayList() : operators;
    }

    @Override
    public boolean test(Object o) {
        return type.getPredicate().test(operators, o);
    }
}
