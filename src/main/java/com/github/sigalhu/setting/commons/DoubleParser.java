package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import com.github.sigalhu.setting.reporters.SupplerReporter;
import org.apache.commons.lang3.math.NumberUtils;

import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class DoubleParser extends BaseSettingParser<Double> implements AdaptedTypeReporter, SupplerReporter {

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

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Double.class, double.class};
    }

    @Override
    public Function<String, ? extends SettingParser> parserSuppler() {
        return DoubleParser::new;
    }
}
