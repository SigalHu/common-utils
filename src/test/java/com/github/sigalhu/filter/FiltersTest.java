package com.github.sigalhu.filter;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * @author huxujun
 * @date 2019-08-01
 */
public class FiltersTest {

    @Test
    public void test() {
        System.err.println(JSON.toJSONString(Filters.eq("1", Lists.newArrayList(1, 2))));
    }
}