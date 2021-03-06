package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Objects;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Boolean.class, boolean.class})
public class BooleanParser extends BaseSettingParser<Boolean> {

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
}
