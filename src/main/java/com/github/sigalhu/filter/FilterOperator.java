package com.github.sigalhu.filter;

/**
 * @author huxujun
 * @date 2019-07-29
 */
public enum FilterOperator {

    /**
     * 关系运算符
     */
    EQ("eq", "等于"),
    CONTAIN("contain", "包含"),
    LT("lt", "小于"),
    LTE("lte", "小于等于"),
    GT("gt", "大于"),
    GTE("gte", "大于等于"),
    IN("in", "属于"),

    /**
     * 逻辑运算符
     */
    AND("and", "与"),
    OR("or", "或"),
    NOT("not", "非"),
    ;


    private String code;
    private String desc;

    FilterOperator(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
