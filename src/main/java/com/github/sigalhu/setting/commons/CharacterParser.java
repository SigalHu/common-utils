package com.github.sigalhu.setting.commons;


import com.github.sigalhu.setting.reporters.AdaptedTypeReporter;
import com.github.sigalhu.setting.reporters.SupplerReporter;

import java.util.Objects;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public class CharacterParser extends BaseSettingParser<Character> implements AdaptedTypeReporter, SupplerReporter {

    public CharacterParser(String setting) {
        this(null, setting);
    }

    public CharacterParser(String setting, Character defaultValue) {
        this(null, setting, defaultValue);
    }

    public CharacterParser(String prefix, String setting) {
        this(prefix, setting, null);
    }

    public CharacterParser(String prefix, String setting, Character defaultValue) {
        super(prefix, setting, defaultValue);
    }

    @Override
    protected Character parseString(String str, Character defaultValue) {
        return Objects.nonNull(str) && str.length() == 1 ? str.charAt(0) : defaultValue;
    }

    @Override
    public Class[] adaptedTypes() {
        return new Class[]{Character.class, char.class};
    }

    @Override
    public Function<String, ? extends SettingParser> parserSuppler() {
        return CharacterParser::new;
    }
}
