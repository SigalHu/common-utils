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

    private long startTime;
    @Getter
    private CostDetail costDetail;

    public DetailCostTimer(CostDetail costDetail) {
        this.costDetail = costDetail;
        start();
    }

    @Override
    public void start() {
        startTime = System.currentTimeMillis();
    }

    @Override
    public void end() {
        this.costDetail.setCost(System.currentTimeMillis() - startTime);
    }

    @Override
    public Long getCost() {
        return costDetail.getCost();
    }

    @Override
    public CostTimer childCostTimer(String name) {
        CostDetail costDetail = new CostDetail();
        costDetail.setName(name);
        if (CollectionUtils.isEmpty(this.costDetail.getDetails())) {
            this.costDetail.setDetails(Lists.newArrayList(costDetail));
        } else {
            this.costDetail.getDetails().add(costDetail);
        }
        return new DetailCostTimer(costDetail);
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
