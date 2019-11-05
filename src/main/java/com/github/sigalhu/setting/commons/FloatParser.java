package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class FloatParser extends BaseSettingParser<Float> implements AdaptedTypeReporter {

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

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Float.class, float.class};
    }
}
