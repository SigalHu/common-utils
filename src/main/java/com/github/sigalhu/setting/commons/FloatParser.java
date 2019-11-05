package com.github.sigalhu.setting.commons;

import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class FloatParser extends BaseSettingParser<Float> {

    public FloatParser(String setting) {
        this(null, setting);
    }

    public FloatParser(String setting, Float defaultValue) {
        this(null, setting, defaultValue);
    }

    public FloatParser(String prefix, String setting) {
        this(prefix, setting, 0F);
    }

    public FloatParser(String prefix, String setting, Float defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Float parseString(String str, Float defaultValue) {
        return NumberUtils.toFloat(str, defaultValue);
    }
}
