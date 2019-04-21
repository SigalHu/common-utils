package com.github.sigalhu.pipeline;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public enum ProcessorResult {

    /**
     * 流水线执行结果
     */
    SUCCESS(0, "执行成功"),
    FAIL(1, "执行失败"),
    CONTINUE(2, "继续执行"),
    ;

    private Integer code;
    private String desc;

    ProcessorResult(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public Integer getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }
}
