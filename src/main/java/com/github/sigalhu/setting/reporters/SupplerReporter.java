package com.github.sigalhu.setting.reporters;

import com.github.sigalhu.setting.commons.SettingParser;

import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/5
 */
public interface SupplerReporter {

    /**
     * 获取解析器的生成器
     *
     * @return
     */
    Function<String, ? extends SettingParser> parserSuppler();
}
