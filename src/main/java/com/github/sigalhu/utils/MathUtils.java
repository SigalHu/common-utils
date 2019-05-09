package com.github.sigalhu.utils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author huxujun
 * @date 2019-05-09
 */
public class MathUtils {

    public static Optional<Double> percent(Number part, Number total) {
        if (Objects.isNull(part) || Objects.isNull(total)) {
            return Optional.empty();
        }
        double p = part.doubleValue();
        double t = total.doubleValue();
        return Optional.ofNullable(t == 0 ? null : p / t);
    }

    public static Optional<Long> tps(Number lastCount, long lastMillis, Number currentCount, long currentMillis) {
        if (Objects.isNull(lastCount) || Objects.isNull(currentCount) || lastMillis < 0 || currentMillis < 0) {
            return Optional.empty();
        }
        long current = currentCount.longValue();
        long last = lastCount.longValue();
        long millis = currentMillis - lastMillis;
        if (last < 0 || current < last || millis <= 0) {
            return Optional.empty();
        }
        return Optional.of((current - last) * 1000 / millis);
    }

    public static Optional<Long> tps(Number count, long millis) {
        if (Objects.isNull(count) || millis <= 0) {
            return Optional.empty();
        }
        long c = count.longValue();
        if (c < 0) {
            return Optional.empty();
        }
        return Optional.of(c * 1000 / millis);
    }

    public static Optional<Long> latency(Number lastLatency, Number lastCount, Number currentLatency, Number currentCount) {
        if (Objects.isNull(lastLatency) || Objects.isNull(lastCount) || Objects.isNull(currentLatency) || Objects.isNull(currentCount)) {
            return Optional.empty();
        }
        long current = currentLatency.longValue();
        long last = lastLatency.longValue();
        long count = currentCount.longValue() - lastCount.longValue();
        if (last < 0 || current < last || count <= 0) {
            return Optional.empty();
        }
        return Optional.of((current - last) / count);
    }

    public static Optional<Long> latency(Number latency, Number count) {
        if (Objects.isNull(latency) || Objects.isNull(count)) {
            return Optional.empty();
        }
        long l = latency.longValue();
        long c = count.longValue();
        if (l < 0 || c <= 0) {
            return Optional.empty();
        }
        return Optional.of(l / c);
    }

    public static Optional<Long> incr(Long lastValue, Long currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue - lastValue);
    }
}
