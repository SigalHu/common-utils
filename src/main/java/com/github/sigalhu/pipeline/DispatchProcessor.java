package com.github.sigalhu.pipeline;

import com.github.sigalhu.utils.Assert;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * DispatchProcessor 为分发器，可以依次处理传入的 Processors，每个 Processor 互不影响，都会被执行
 *
 * @author huxujun
 * @date 2019-04-21
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class DispatchProcessor<T> implements Processor<T> {

    @Setter
    private String name = "default-dispatch-processor";

    @Setter
    private List<Processor<? super T>> processors;

    @Override
    public ProcessorResult handle(T context) {
        if (CollectionUtils.isEmpty(this.processors)) {
            log.warn("The processors of DispatchProcessor {} is empty!", this.name());
            return ProcessorResult.SUCCESS;
        }

        for (Processor<? super T> processor : processors) {
            try {
                if (processor.skip(context)) {
                    continue;
                }
                long start = System.currentTimeMillis();
                ProcessorResult result = processor.handle(context);
                if (processor.printCost()) {
                    log.info("The processor {} of DispatchProcessor {} has been handled, cost={}ms, result={}",
                            processor.name(), this.name(), System.currentTimeMillis() - start, result);
                }
                Assert.notNull(result, "The processorResult must not be null!");
                Assert.isFalse(ProcessorResult.FAIL.equals(result), "The processorResult must not be fail!");
            } catch (Exception e) {
                log.error("Error to handle the processor {} of DispatchProcessor {}, error={}",
                        processor.name(), this.name(), e.getMessage(), e);
            }
        }
        return ProcessorResult.SUCCESS;
    }

    @Override
    public String name() {
        return this.name;
    }
}
