package com.github.sigalhu.pipeline;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public interface Processor<T> {

    /**
     * 是否跳过该Processor
     *
     * @param context
     * @return
     */
    default boolean skip(T context) {
        return false;
    }

    /**
     * 执行Processor的处理逻辑
     *
     * @param context
     * @return
     */
    ProcessorResult handle(T context);

    /**
     * Processor的名称
     *
     * @return
     */
    default String name() {
        return "default-processor";
    }

    /**
     * 是否打印该Processor的耗时
     *
     * @return
     */
    default boolean printCost() {
        return false;
    }
}
