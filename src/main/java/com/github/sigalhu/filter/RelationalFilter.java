package com.github.sigalhu.filter;

import com.google.common.collect.Lists;
import lombok.Getter;

import java.util.List;

/**
 * @author huxujun
 * @date 2019-07-29
 */
@Getter
public class RelationalFilter extends BaseFilter {

    private List<BaseFilter> filters;

    RelationalFilter(FilterOperator operator, List<BaseFilter> filters) {
        super(operator);
        this.filters = filters == null ? Lists.newArrayList() : filters;
    }
}
