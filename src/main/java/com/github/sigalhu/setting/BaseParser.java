package com.github.sigalhu.setting;

import org.apache.commons.collections4.MapUtils;

import java.util.Map;
import java.util.Objects;

/**
 * @author huxujun
 * @date 2019/11/2
 */
public abstract class BaseParser<T> {

    /**
     * 配置项
     */
    private String setting;

    public BaseParser(String prefix, String setting) {
        if (Objects.isNull(prefix)) {
            prefix = "";
        }
        this.setting = prefix + setting;
    }

    protected T defaultValue() {
        return null;
    }

    public T parse(Map<String, String> settings) {
        return parse(settings, defaultValue());
    }

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
     * @param str 对应配置的字符串值
     * @param defaultValue 对应配置的默认值
     * @return 配置值对象
     */
    abstract protected T parseString(String str, T defaultValue);
}
