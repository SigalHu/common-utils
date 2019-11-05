package com.github.sigalhu.setting.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huxujun
 * @date 2019/11/5
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ParserRegister {

    /**
     * 获取解析器适配的类型
     *
     * @return
     */
    Class[] value();
}
