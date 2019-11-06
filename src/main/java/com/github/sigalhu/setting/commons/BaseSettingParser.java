package com.github.sigalhu.setting.commons;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public abstract class BaseSettingParser<T> implements SettingParser<T> {

    /**
     * 指定配置
     */
    protected String setting;

    /**
     * 默认值
     */
    protected T defaultValue;

    public BaseSettingParser(String setting) {
        this(null, setting);
    }

    public BaseSettingParser(String setting, T defaultValue) {
        this(null, setting, defaultValue);
    }

    public BaseSettingParser(String prefix, String setting) {
        this(prefix, setting, null);
    }

    public BaseSettingParser(String prefix, String setting, T defaultValue) {
        if (Objects.isNull(prefix)) {
            prefix = "";
        }
        if (!StringUtils.isEmpty(prefix) && !prefix.endsWith(DELIMITER)) {
            prefix += ".";
        }
        this.setting = prefix + setting;
        this.defaultValue = defaultValue;
    }

    @Override
    public String setting() {
        return setting;
    }

    @Override
    public String valueOf(T objValue) {
        if (Objects.isNull(objValue)) {
            return null;
        }
        return String.valueOf(objValue);
    }

    @Override
    public T parse(Map<String, String> settings) {
        return parse(settings, defaultValue);
    }

    @Override
    public T parse(Map<String, String> settings, T defaultValue) {
        if (MapUtils.isEmpty(settings)) {
            return defaultValue;
        }
        String str = settings.get(setting);
        if (Objects.isNull(str)) {
            return defaultValue;
        }
        try {
            return parseString(str, defaultValue);
        } catch (Exception ex) {
            return defaultValue;
        }
    }

    /**
     * 将对应配置的字符串值解析为具体对象
     *
     * @param str          对应配置的字符串值
     * @param defaultValue 对应配置的默认值
     * @return 配置值对象
     */
    abstract protected T parseString(String str, T defaultValue);
}
