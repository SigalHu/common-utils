package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import com.github.sigalhu.utils.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Float.class, float.class})
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
