package com.github.sigalhu.setting;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class DoubleParser extends BaseParser<Double> {

    public DoubleParser(String prefix, String setting) {
        super(prefix, setting);
    }

    @Override
    protected Double defaultValue() {
        return 0D;
    }

    @Override
    protected Double parseString(String str, Double defaultValue) {
        return NumberUtils.toDouble(str, defaultValue);
    }
}
