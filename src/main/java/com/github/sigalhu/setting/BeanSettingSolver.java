package com.github.sigalhu.setting;

import com.github.sigalhu.setting.annotations.SettingConfiguration;
import com.github.sigalhu.setting.annotations.SettingField;
import com.github.sigalhu.setting.commons.*;
import com.github.sigalhu.utils.BeanUtils;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/4
 */
public class BeanSettingSolver {

    private Map<Class, List<BeanInfo>> beanInfoMap = new HashMap<>();
    private Map<Class, Function<String, ? extends SettingParser>> parserMap = new HashMap<>();

    public BeanSettingSolver() {
        registerParserMap();
    }

    private void registerParserMap() {
        registerParser(Boolean.class, BooleanParser::new);
        registerParser(boolean.class, BooleanParser::new);
        registerParser(Character.class, CharacterParser::new);
        registerParser(char.class, CharacterParser::new);
        registerParser(Byte.class, ByteParser::new);
        registerParser(byte.class, ByteParser::new);
        registerParser(Short.class, ShortParser::new);
        registerParser(short.class, ShortParser::new);
        registerParser(Integer.class, IntegerParser::new);
        registerParser(int.class, IntegerParser::new);
        registerParser(Long.class, LongParser::new);
        registerParser(long.class, LongParser::new);
        registerParser(Float.class, FloatParser::new);
        registerParser(float.class, FloatParser::new);
        registerParser(Double.class, DoubleParser::new);
        registerParser(double.class, DoubleParser::new);
        registerParser(String.class, StringParser::new);
    }

    public void registerParser(Class clazz, Function<String, ? extends SettingParser> parserSuppler) {
        parserMap.put(clazz, parserSuppler);
    }

    public void register(String packagePrefix) {
        Reflections reflections = new Reflections(packagePrefix);
        reflections.getTypesAnnotatedWith(SettingConfiguration.class).forEach(this::register);
    }

    public void register(Class clazz) {
        if (Objects.isNull(clazz) || beanInfoMap.containsKey(clazz)) {
            return;
        }

        SettingConfiguration config = (SettingConfiguration) clazz.getAnnotation(SettingConfiguration.class);
        Preconditions.checkNotNull(config,
                String.format("The %s must be annotated with @SettingConfiguration!", clazz.getName()));
        String prefix = config.prefix();

        List<BeanInfo> beanInfos = new ArrayList<>();
        for (Field field : ReflectionUtils.getAllFields(clazz)) {
            field.setAccessible(true);
            Pair<Method, Method> rwPair = reflectReaderAndWriter(field.getName(), clazz);
            SettingFieldInfo settingFieldInfo = buildSettingFieldInfo(field.getAnnotation(SettingField.class),
                    Objects.nonNull(rwPair.getRight()) ? rwPair.getRight().getAnnotation(SettingField.class) : null);
            if (Objects.isNull(settingFieldInfo)) {
                continue;
            }
            Function<String, ? extends SettingParser> parserSuppler;
            if (Objects.isNull(settingFieldInfo.getParser())) {
                parserSuppler = parserMap.get(field.getType());
            } else {
                try {
                    Constructor<? extends SettingParser> c = settingFieldInfo.getParser().getConstructor(String.class);
                    parserSuppler = BeanUtils.function(c, Function.class);
                } catch (Exception ex) {
                    throw new IllegalStateException(ex);
                }
            }
            Preconditions.checkNotNull(parserSuppler, String.format(
                    "The parser of @SettingConfiguration must be required, and the annotated field is %s#%s",
                    clazz.getName(), field.getName()));

            BeanInfo beanInfo = new BeanInfo();
            beanInfo.setField(field);
            beanInfo.setParser(parserSuppler.apply(prefix + settingFieldInfo.getSetting()));
            beanInfo.setGetter(BeanUtils.function(rwPair.getLeft(), Function.class));
            beanInfo.setSetter(BeanUtils.function(rwPair.getRight(), BiConsumer.class));
            beanInfos.add(beanInfo);
        }
        beanInfoMap.put(clazz, beanInfos);
    }

    private Pair<Method, Method> reflectReaderAndWriter(String fieldName, Class clazz) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
            return Pair.of(descriptor.getReadMethod(), descriptor.getWriteMethod());
        } catch (Exception ex) {
        }
        return Pair.of(null, null);
    }

    private SettingFieldInfo buildSettingFieldInfo(SettingField... fields) {
        String setting = null;
        Class<? extends SettingParser> parser = null;
        for (SettingField field : fields) {
            if (Objects.isNull(field)) {
                continue;
            }
            if (StringUtils.isNotEmpty(field.value())) {
                setting = field.value();
            }
            if (!VoidParser.class.equals(field.parser())) {
                parser = field.parser();
            }
        }
        if (Objects.isNull(setting)) {
            return null;
        }
        return new SettingFieldInfo(setting, parser);
    }

    @Data
    private static class BeanInfo {
        private Field field;
        private Function getter;
        private BiConsumer setter;
        private SettingParser parser;
    }

    @Data
    @AllArgsConstructor
    private static class SettingFieldInfo {
        private String setting;
        private Class<? extends SettingParser> parser;
    }
}
