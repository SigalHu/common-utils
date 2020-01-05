package com.github.sigalhu.utils.fastjson;

import lombok.Data;

import java.lang.reflect.Type;

/**
 * @author huxujun
 * @date 2019/12/14
 */
@Data
public class JsonPathInfo {

    private String fieldName;
    private Type fieldType;
    private String jsonPath;
}
