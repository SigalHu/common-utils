package com.github.sigalhu.timer.cost;

import lombok.Data;

import java.util.List;

/**
 * @author huxujun
 * @date 2019-08-15
 */
@Data
public class CostDetail {

    private String name;
    private long cost = 0L;
    private String message;
    private List<CostDetail> details;
}
