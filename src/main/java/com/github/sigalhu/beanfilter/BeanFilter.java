package com.github.sigalhu.beanfilter;

/**
 * @author huxujun
 * @date 2019-08-08
 */
public interface BeanFilter {

    /**
     * 判断传入实例是否符合筛选条件
     * @param o
     * @return
     */
    boolean test(Object o);
}
