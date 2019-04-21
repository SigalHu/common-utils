package com.github.sigalhu.pipeline;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author huxujun
 * @date 2019-04-21
 */
public class PipelineTest {

    private Processor<ProcessorContext> processor;

    @Before
    public void setUp() throws Exception {
        this.processor = new PipelineProcessor<>(
                "pipeline-processor-1",
                Arrays.asList(
                        new TestPipelineProcessor("test-pipeline-processor-1"),
                        new TestPipelineProcessor("test-pipeline-processor-2"),
                        new DispatchProcessor<>(
                                "dispatch-processor-1",
                                Arrays.asList(
                                        new TestDispatchProcessor("test-dispatch-processor-1"),
                                        new PipelineProcessor<>(
                                                "pipeline-processor-2",
                                                Arrays.asList(
                                                        new TestPipelineProcessor("test-pipeline-processor-3"),
                                                        new TestPipelineProcessor("test-pipeline-processor-4")
                                                )),
                                        // This processor will be handled!
                                        new TestDispatchProcessor("test-dispatch-processor-2")

                                )),
                        // This processor will not be handled!
                        new TestPipelineProcessor("test-pipeline-processor-5")
                ));
    }

    @Test
    public void handle() {
        ProcessorContext context = new ProcessorContext();
        ProcessorResult result = processor.handle(context);
        Assert.assertEquals(ProcessorResult.SUCCESS, result);
        Assert.assertNotNull(context.getNames());
        Assert.assertArrayEquals(new String[]{
                "test-pipeline-processor-1",
                "test-pipeline-processor-2",
                "test-dispatch-processor-1",
                "test-pipeline-processor-3",
                "test-pipeline-processor-4",
                "test-dispatch-processor-2"
        }, context.getNames().toArray(new String[0]));
    }

    @Data
    public static class ProcessorContext {
        List<String> names = new ArrayList<>();
    }

    @AllArgsConstructor
    public static class TestPipelineProcessor implements Processor<ProcessorContext> {

        private String name;

        @Override
        public ProcessorResult handle(ProcessorContext context) {
            context.getNames().add(this.name());
            return ProcessorResult.CONTINUE;
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public boolean printCost() {
            return true;
        }
    }

    @AllArgsConstructor
    public static class TestDispatchProcessor implements Processor<ProcessorContext> {

        private String name;

        @Override
        public ProcessorResult handle(ProcessorContext context) {
            context.getNames().add(this.name());
            return ProcessorResult.SUCCESS;
        }

        @Override
        public String name() {
            return this.name;
        }

        @Override
        public boolean printCost() {
            return true;
        }
    }
}
