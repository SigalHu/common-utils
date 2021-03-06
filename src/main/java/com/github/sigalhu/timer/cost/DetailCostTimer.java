package com.github.sigalhu.timer.cost;

import com.google.common.collect.Lists;
import lombok.Getter;
import org.apache.commons.collections4.CollectionUtils;

import java.io.IOException;

/**
 * @author huxujun
 * @date 2019-08-15
 */
public class DetailCostTimer implements CostTimer {

    private long cost = 0L;
    private long startTime;
    @Getter
    private CostDetail costDetail;

    public DetailCostTimer(CostDetail costDetail) {
        this.costDetail = costDetail;
        start();
    }

    @Override
    public void start() {
        startTime = System.nanoTime();
    }

    @Override
    public void end() {
        long newCost = System.nanoTime() - startTime;
        cost += newCost;
        costDetail.setCost(cost / 1000000);
    }

    @Override
    public long getCost() {
        return costDetail.getCost();
    }

    @Override
    public CostTimer childCostTimer(String name) {
        CostDetail detail = new CostDetail();
        detail.setName(name);
        if (CollectionUtils.isEmpty(this.costDetail.getDetails())) {
            this.costDetail.setDetails(Lists.newArrayList(detail));
        } else {
            this.costDetail.getDetails().add(detail);
        }
        return new DetailCostTimer(detail);
    }

    @Override
    public void close() throws IOException {
        end();
    }

    @Override
    public void message(String message) {
        this.costDetail.setMessage(message);
    }
}
