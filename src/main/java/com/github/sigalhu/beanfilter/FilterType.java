package com.github.sigalhu.beanfilter;

import java.util.Collection;
import java.util.Objects;
import java.util.function.BiPredicate;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public enum FilterType {

    /**
     * 关系运算符
     */
    EQ("eq", "等于", Objects::equals),
    CONTAIN("contain", "包含", (l, r) -> ((Collection) l).contains(r)),
    LT("lt", "小于", (l, r) -> ((Comparable) l).compareTo(r) < 0),
    LTE("lte", "小于等于", (l, r) -> ((Comparable) l).compareTo(r) <= 0),
    GT("gt", "大于", (l, r) -> ((Comparable) l).compareTo(r) > 0),
    GTE("gte", "大于等于", (l, r) -> ((Comparable) l).compareTo(r) >= 0),
    IN("in", "属于", (l, r) -> ((Collection) r).contains(l)),

    /**
     * 逻辑运算符
     */
    AND("and", "与", (f, o) -> {
        for (BeanFilter operator : ((Collection<BeanFilter>) f)) {
            if (!operator.test(o)) {
                return false;
            }
        }
        return true;
    }),
    OR("or", "或", (f, o) -> {
        for (BeanFilter operator : ((Collection<BeanFilter>) f)) {
            if (operator.test(o)) {
                return true;
            }
        }
        return false;
    }),
    NOT("not", "非", (f, o) -> !AND.getPredicate().test(f, o)),
    ;


    private String code;
    private String desc;
    private BiPredicate predicate;

    FilterType(String code, String desc, BiPredicate predicate) {
        this.code = code;
        this.desc = desc;
        this.predicate = predicate;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    public BiPredicate getPredicate() {
        return predicate;
    }
}
