package com.github.sigalhu.timer.cost;

import java.io.IOException;

/**
 * @author huxujun
 * @date 2019-08-15
 */
public class DummyCostTimer extends DetailCostTimer {

    private static final CostTimer DUMMY_COST_TIMER = new CostTimer() {

        @Override
        public void start() {
        }

        @Override
        public void end() {
        }

        @Override
        public Long getCost() {
            return null;
        }

        @Override
        public CostTimer childCostTimer(String name) {
            return this;
        }

        @Override
        public void close() throws IOException {
        }
    };

    public DummyCostTimer(CostDetail costDetail) {
        super(costDetail);
    }

    @Override
    public CostTimer childCostTimer(String name) {
        return DUMMY_COST_TIMER;
    }
}