package com.github.sigalhu.beanfilter;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public abstract class BaseFilter implements BeanFilter {

    protected FilterType type;

    protected BaseFilter(FilterType type) {
        this.type = type;
    }
}
