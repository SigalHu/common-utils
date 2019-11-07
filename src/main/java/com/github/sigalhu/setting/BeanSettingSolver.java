package com.github.sigalhu.setting;

import com.github.sigalhu.setting.annotations.ParserRegister;
import com.github.sigalhu.setting.annotations.SettingConfiguration;
import com.github.sigalhu.setting.annotations.SettingField;
import com.github.sigalhu.setting.commons.*;
import com.github.sigalhu.utils.BeanUtils;
import com.google.common.base.Preconditions;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;
import java.util.function.Function;

/**
 * @author huxujun
 * @date 2019/11/4
 */
public class BeanSettingSolver {

    private Map<Class, List<BeanInfo>> beanInfoMap = new ConcurrentHashMap<>();
    private Map<Class, Function<String, ? extends SettingParser>> parserMap = new ConcurrentHashMap<>();

    public BeanSettingSolver() {
        registerParser(SettingParser.class.getPackage().getName());
    }

    public Map<String, String> collectSettings(Object value) {
        List<BeanInfo> beanInfos = getBeanInfos(value.getClass());
        Map<String, String> settings = new HashMap<>();
        for (BeanInfo beanInfo : beanInfos) {
            String settingName = beanInfo.getParser().setting();
            Object settingValue = beanInfo.get(value);
            settings.put(settingName, beanInfo.getParser().valueOf(settingValue));
        }
        return settings;
    }

    public <T> T parse(Map<String, String> settings, T defaultValue) {
        List<BeanInfo> beanInfos = getBeanInfos(defaultValue.getClass());
        for (BeanInfo beanInfo : beanInfos) {
            Object defaultSetting = beanInfo.get(defaultValue);
            Object setting = beanInfo.getParser().parse(settings, defaultSetting);
            if (!Objects.equals(setting, defaultSetting)) {
                beanInfo.set(defaultValue, setting);
            }
        }
        return defaultValue;
    }

    public void registerParser(String packagePrefix) {
        Reflections reflections = new Reflections(packagePrefix);
        reflections.getTypesAnnotatedWith(ParserRegister.class).forEach(parserClazz -> {
            ParserRegister register = parserClazz.getAnnotation(ParserRegister.class);
            Preconditions.checkArgument(ArrayUtils.isNotEmpty(register.value()),
                    String.format("The value of @ParserRegister must be required, and the annotated class is %s", parserClazz.getName()));
            try {
                Function<String, ? extends SettingParser> parserSuppler = BeanUtils.function(parserClazz.getConstructor(String.class), Function.class);
                for (Class clazz : register.value()) {
                    registerParser(clazz, parserSuppler);
                }
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        });
    }

    public void registerParser(Class clazz, Function<String, ? extends SettingParser> parserSuppler) {
        Preconditions.checkArgument(!parserMap.containsKey(clazz),
                String.format("The %s have been registered in %s", clazz.getName(), getClass().getName()));
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
        beanInfoMap.put(clazz, buildBeanInfos(clazz));
    }

    private List<BeanInfo> getBeanInfos(Class clazz) {
        return beanInfoMap.computeIfAbsent(clazz, this::buildBeanInfos);
    }

    private List<BeanInfo> buildBeanInfos(Class clazz) {
        SettingConfiguration config = (SettingConfiguration) clazz.getAnnotation(SettingConfiguration.class);
        Preconditions.checkNotNull(config,
                String.format("The %s must be annotated with @SettingConfiguration!", clazz.getName()));
        String prefix = config.prefix();

        List<BeanInfo> beanInfos = new ArrayList<>();
        for (Field field : ReflectionUtils.getAllFields(clazz)) {
            field.setAccessible(true);
            Pair<Method, Method> rwPair = reflectReaderAndWriter(field.getName(), clazz);
            SettingFieldInfo settingFieldInfo = buildSettingFieldInfo(field, field.getAnnotation(SettingField.class),
                    Objects.nonNull(rwPair.getRight()) ? rwPair.getRight().getAnnotation(SettingField.class) : null);
            if (Objects.isNull(settingFieldInfo)) {
                continue;
            }
            Function<String, ? extends SettingParser> parserSuppler;
            if (Objects.isNull(settingFieldInfo.getParser())) {
                parserSuppler = parserMap.get(field.getType());
            } else {
                try {
                    Class<? extends SettingParser> parserClazz = settingFieldInfo.getParser();
                    ParserRegister register = parserClazz.getAnnotation(ParserRegister.class);
                    if (Objects.nonNull(register)) {
                        Preconditions.checkArgument(ArrayUtils.contains(register.value(), field.getType()),
                                String.format("The %s %s#%s is not supported by %s!", field.getType().getName(),
                                        clazz.getName(), field.getName(), parserClazz.getName()));
                    }
                    Constructor<? extends SettingParser> c = parserClazz.getConstructor(String.class);
                    parserSuppler = BeanUtils.function(c, Function.class);
                    registerParser(field.getType(), parserSuppler);
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
        return beanInfos;
    }

    private Pair<Method, Method> reflectReaderAndWriter(String fieldName, Class clazz) {
        try {
            PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, clazz);
            return Pair.of(descriptor.getReadMethod(), descriptor.getWriteMethod());
        } catch (Exception ex) {
        }
        return Pair.of(null, null);
    }

    private SettingFieldInfo buildSettingFieldInfo(Field field, SettingField... settingFields) {
        String setting = null;
        Class<? extends SettingParser> parser = null;
        boolean settingExist = false;
        for (SettingField settingField : settingFields) {
            if (Objects.isNull(settingField)) {
                continue;
            }
            settingExist = true;
            if (StringUtils.isNotEmpty(settingField.value())) {
                setting = settingField.value();
            }
            if (!VoidParser.class.equals(settingField.parser())) {
                parser = settingField.parser();
            }
        }
        if (!settingExist) {
            return null;
        }
        if (StringUtils.isEmpty(setting)) {
            setting = field.getName();
        }
        return new SettingFieldInfo(setting, parser);
    }

    @Data
    private static class BeanInfo {
        private Field field;
        private Function<Object, Object> getter;
        private BiConsumer<Object, Object> setter;
        private SettingParser parser;

        public Object get(Object obj) {
            try {
                if (Objects.nonNull(getter)) {
                    return getter.apply(obj);
                }
                return field.get(obj);
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }

        public void set(Object obj, Object value) {
            try {
                if (Objects.nonNull(setter)) {
                    setter.accept(obj, value);
                    return;
                }
                field.set(obj, value);
            } catch (Exception ex) {
                throw new IllegalStateException(ex);
            }
        }
    }

    @Data
    @AllArgsConstructor
    private static class SettingFieldInfo {
        private String setting;
        private Class<? extends SettingParser> parser;
    }
}
