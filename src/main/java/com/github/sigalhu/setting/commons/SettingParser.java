package com.github.sigalhu.setting.commons;

import java.util.Map;

/**
 * @author huxujun
 * @date 2019/11/3
 */
public interface SettingParser<T> {

    String DELIMITER = ".";

    /**
     * 获取指定配置项
     *
     * @return
     */
    String setting();

    /**
     * 将配置的实例转化为字符串
     *
     * @param objValue
     * @return
     */
    String valueOf(T objValue);

    /**
     * 解析指定配置
     *
     * @param settings 所有配置
     * @return 指定配置的解析结果
     */
    T parse(Map<String, String> settings);

    /**
     * 解析指定配置，解析失败则返回默认结果
     *
     * @param settings 所有配置
     * @param defaultValue 默认结果
     * @return 指定配置的解析结果
     */
    T parse(Map<String, String> settings, T defaultValue);
}
