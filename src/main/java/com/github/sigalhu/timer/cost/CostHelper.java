package com.github.sigalhu.timer.cost;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huxujun
 * @date 2019-08-15
 */
public class CostHelper {

    private boolean hasDetails;
    @Getter
    private List<CostDetail> details;

    public CostHelper(boolean hasDetails) {
        this.hasDetails = hasDetails;
        this.details = new ArrayList<>();
    }

    public CostTimer createCostTimer(String name) {
        CostDetail costDetail = new CostDetail();
        costDetail.setName(name);
        details.add(costDetail);
        return hasDetails ? new DetailCostTimer(costDetail) : new DummyCostTimer(costDetail);
    }
}
