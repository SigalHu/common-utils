package com.github.sigalhu.utils;

import com.alibaba.fastjson.JSON;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * @author huxujun
 * @date 2019-08-18
 */
public class JsonUtilsTest {

    @Test
    public void leftEquals() {
        String left = "{\"height\":1.75,\"id\":1,\"name\":\"student\",\"phone\":\"01234567\",\"transcript\":[{\"name\":\"mathematics\"},{\"name\":\"physics\"},{\"name\":\"organism\"},{\"name\":\"geography\"}],\"weight\":65.5}";
        String right = "{\"age\":18,\"height\":1.75,\"id\":1,\"name\":\"student\",\"phone\":\"01234567\",\"transcript\":[{\"name\":\"mathematics\",\"score\":100},{\"name\":\"physics\",\"score\":99},{\"name\":\"organism\",\"score\":98},{\"name\":\"geography\",\"score\":97}],\"weight\":65.5}";
        assertTrue(JsonUtils.leftEquals(JSON.parseObject(left), JSON.parseObject(right)));
    }
}