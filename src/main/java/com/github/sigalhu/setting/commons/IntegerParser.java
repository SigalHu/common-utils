package com.github.sigalhu.setting.commons;

import com.github.sigalhu.setting.annotations.ParserRegister;
import com.github.sigalhu.utils.NumberUtils;

/**
 * @author huxujun
 * @date 2019/11/2
 */
@ParserRegister({Integer.class, int.class})
public class IntegerParser extends BaseSettingParser<Integer> {

    public IntegerParser(String setting) {
        this(null, setting);
    }

    public IntegerParser(String setting, Integer defaultValue) {
        this(null, setting, defaultValue);
    }

    public IntegerParser(String prefix, String setting) {
        this(prefix, setting, 0);
    }

    public IntegerParser(String prefix, String setting, Integer defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Integer parseString(String str, Integer defaultValue) {
        return NumberUtils.toInteger(str, defaultValue);
    }
}
