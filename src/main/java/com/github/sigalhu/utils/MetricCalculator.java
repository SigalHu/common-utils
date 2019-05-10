package com.github.sigalhu.utils;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;
import java.util.Optional;

/**
 * @author huxujun
 * @date 2019-05-09
 */
public class MetricCalculator {

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

    public static Optional<Double> incr(Float lastValue, Float currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue.doubleValue() - lastValue.doubleValue());
    }

    public static Optional<Double> incr(Double lastValue, Double currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue - lastValue);
    }

    public static Optional<Long> incr(Byte lastValue, Byte currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue.longValue() - lastValue.longValue());
    }

    public static Optional<Long> incr(Short lastValue, Short currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue.longValue() - lastValue.longValue());
    }

    public static Optional<Long> incr(Integer lastValue, Integer currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue.longValue() - lastValue.longValue());
    }

    public static Optional<Long> incr(Long lastValue, Long currentValue) {
        if (Objects.isNull(lastValue) || Objects.isNull(currentValue)) {
            return Optional.empty();
        }
        return Optional.of(currentValue - lastValue);
    }

    public static Optional<Float> sum(Float... values) {
        if (ArrayUtils.isEmpty(values)) {
            return Optional.empty();
        }
        float sum = 0F;
        boolean allNull = true;
        for (Float value : values) {
            if (Objects.nonNull(value)) {
                allNull = false;
                sum += value;
            }
        }
        return Optional.ofNullable(allNull ? null : sum);
    }

    public static Optional<Double> sum(Double... values) {
        if (ArrayUtils.isEmpty(values)) {
            return Optional.empty();
        }
        double sum = 0F;
        boolean allNull = true;
        for (Double value : values) {
            if (Objects.nonNull(value)) {
                allNull = false;
                sum += value;
            }
        }
        return Optional.ofNullable(allNull ? null : sum);
    }

    public static Optional<Byte> sum(Byte... values) {
        if (ArrayUtils.isEmpty(values)) {
            return Optional.empty();
        }
        byte sum = 0;
        boolean allNull = true;
        for (Byte value : values) {
            if (Objects.nonNull(value)) {
                allNull = false;
                sum += value;
            }
        }
        return Optional.ofNullable(allNull ? null : sum);
    }

    public static Optional<Short> sum(Short... values) {
        if (ArrayUtils.isEmpty(values)) {
            return Optional.empty();
        }
        short sum = 0;
        boolean allNull = true;
        for (Short value : values) {
            if (Objects.nonNull(value)) {
                allNull = false;
                sum += value;
            }
        }
        return Optional.ofNullable(allNull ? null : sum);
    }

    public static Optional<Integer> sum(Integer... values) {
        if (ArrayUtils.isEmpty(values)) {
            return Optional.empty();
        }
        int sum = 0;
        boolean allNull = true;
        for (Integer value : values) {
            if (Objects.nonNull(value)) {
                allNull = false;
                sum += value;
            }
        }
        return Optional.ofNullable(allNull ? null : sum);
    }

    public static Optional<Long> sum(Long... values) {
        if (ArrayUtils.isEmpty(values)) {
            return Optional.empty();
        }
        long sum = 0L;
        boolean allNull = true;
        for (Long value : values) {
            if (Objects.nonNull(value)) {
                allNull = false;
                sum += value;
            }
        }
        return Optional.ofNullable(allNull ? null : sum);
    }
}
