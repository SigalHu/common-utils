package com.github.sigalhu.pipeline;

import com.github.sigalhu.utils.Assert;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;

/**
 * @author huxujun
 * @date 2019-04-21
 */
@Slf4j
@NoArgsConstructor
@AllArgsConstructor
public class PipelineProcessor<T> implements Processor<T> {

    @Setter
    private String name = "default-pipeline-processor";

    @Setter
    private List<Processor<? super T>> processors;

    @Override
    public ProcessorResult handle(T context) {
        if (CollectionUtils.isEmpty(this.processors)) {
            log.warn("The processors of PipelineProcessor {} is empty!", this.name());
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
                    log.info("The processor {} of PipelineProcessor {} has been handled, cost={}ms, result={}",
                            processor.name(), this.name(), System.currentTimeMillis() - start, result);
                }
                Assert.notNull(result, "The processorResult must not be null!");
                if (ProcessorResult.CONTINUE.equals(result)) {
                    continue;
                }
                return result;
            } catch (Exception e) {
                log.error("Error to handle the processor {} of PipelineProcessor {}, error={}",
                        processor.name(), this.name(), e.getMessage(), e);
                return ProcessorResult.FAIL;
            }
        }
        return ProcessorResult.SUCCESS;
    }

    @Override
    public String name() {
        return this.name;
    }
}
