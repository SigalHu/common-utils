package com.github.sigalhu.setting;

import org.apache.commons.lang3.BooleanUtils;

import java.util.Objects;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class BooleanParser extends BaseSettingParser<Boolean> {

    public BooleanParser(String prefix, String setting) {
        super(prefix, setting);
    }

    @Override
    protected Boolean defaultValue() {
        return false;
    }

    @Override
    protected Boolean parseString(String str, Boolean defaultValue) {
        Boolean value = BooleanUtils.toBooleanObject(str);
        return Objects.isNull(value) ? defaultValue : value;
    }
}
