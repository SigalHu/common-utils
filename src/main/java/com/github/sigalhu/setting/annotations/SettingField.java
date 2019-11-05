package com.github.sigalhu.setting.annotations;


import com.github.sigalhu.setting.commons.SettingParser;
import com.github.sigalhu.setting.commons.VoidParser;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author huxujun
 * @date 2019/11/4
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SettingField {

    String value() default "";

    Class<? extends SettingParser> parser() default VoidParser.class;
}
