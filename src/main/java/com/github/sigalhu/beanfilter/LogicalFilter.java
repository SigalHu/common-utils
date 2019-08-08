package com.github.sigalhu.beanfilter;

import com.github.sigalhu.utils.BeanUtils;

import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public class LogicalFilter extends BaseFilter {

    private String fieldName;
    private Object value;

    LogicalFilter(FilterType type, String fieldName, Object value) {
        super(type);
        this.fieldName = fieldName;
        this.value = value;
    }

    @Override
    public boolean test(Object o) {
        Function function = BeanUtils.getters(o.getClass()).get(fieldName);
        if (function == null) {
            return false;
        }
        return type.getPredicate().test(function.apply(o), value);
    }
}
