package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import com.github.sigalhu.setting.reporters.SupplerReporter;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class BooleanParser extends BaseSettingParser<Boolean> implements AdaptedTypeReporter, SupplerReporter {

    public BooleanParser(String setting) {
        this(null, setting);
    }

    public BooleanParser(String setting, Boolean defaultValue) {
        this(null, setting, defaultValue);
    }

    public BooleanParser(String prefix, String setting) {
        this(prefix, setting, false);
    }

    public BooleanParser(String prefix, String setting, Boolean defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Boolean parseString(String str, Boolean defaultValue) {
        Boolean value = BooleanUtils.toBooleanObject(str);
        return Objects.isNull(value) ? defaultValue : value;
    }

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Boolean.class, boolean.class};
    }

    @Override
    public Function<String, ? extends SettingParser> parserSuppler() {
        return BooleanParser::new;
    }
}
