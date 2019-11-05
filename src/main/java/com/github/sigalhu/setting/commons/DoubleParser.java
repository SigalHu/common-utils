package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Double.class, double.class})
public class DoubleParser extends BaseSettingParser<Double> {

    public DoubleParser(String setting) {
        this(null, setting);
    }

    public DoubleParser(String setting, Double defaultValue) {
        this(null, setting, defaultValue);
    }

    public DoubleParser(String prefix, String setting) {
        this(prefix, setting, 0D);
    }

    public DoubleParser(String prefix, String setting, Double defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Double parseString(String str, Double defaultValue) {
        return NumberUtils.toDouble(str, defaultValue);
    }
}
