package com.github.sigalhu.utils;

import java.util.Objects;

/**
 * @author huxujun
 * @date 2019-05-09
 */
public class MathUtils {

    public static Double percent(Number part, Number total) {
        if (Objects.isNull(part) || Objects.isNull(total)) {
            return null;
        }
        double p = part.doubleValue();
        double t = total.doubleValue();
        return t == 0 ? null : p / t;
    }
}
