package com.github.sigalhu.utils;

import com.github.sigalhu.function.unchecked.*;

import java.util.function.*;

/**
 * @author huxujun
 * @date 2019-04-20
 */
public class Try {

    public static <T, U> BiConsumer<T, U> of(UncheckedBiConsumer<T, U> consumer) {
        return (t, u) -> {
            try {
                consumer.accept(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, U, R> BiFunction<T, U, R> of(UncheckedBiFunction<T, U, R> function) {
        return (t, u) -> {
            try {
                return function.apply(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, U> BiPredicate<T, U> of(UncheckedBiPredicate<T, U> predicate) {
        return (t, u) -> {
            try {
                return predicate.test(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static BooleanSupplier of(UncheckedBooleanSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsBoolean();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Consumer<T> of(UncheckedConsumer<T> consumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoubleBinaryOperator of(UncheckedDoubleBinaryOperator operator) {
        return (left, right) -> {
            try {
                return operator.applyAsDouble(left, right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoubleConsumer of(UncheckedDoubleConsumer consumer) {
        return value -> {
            try {
                consumer.accept(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <R> DoubleFunction<R> of(UncheckedDoubleFunction<R> function) {
        return value -> {
            try {
                return function.apply(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoublePredicate of(UncheckedDoublePredicate predicate) {
        return value -> {
            try {
                return predicate.test(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoubleSupplier of(UncheckedDoubleSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsDouble();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoubleToIntFunction of(UncheckedDoubleToIntFunction function) {
        return value -> {
            try {
                return function.applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoubleToLongFunction of(UncheckedDoubleToLongFunction function) {
        return value -> {
            try {
                return function.applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static DoubleUnaryOperator of(UncheckedDoubleUnaryOperator operator) {
        return operand -> {
            try {
                return operator.applyAsDouble(operand);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> function) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntBinaryOperator of(UncheckedIntBinaryOperator operator) {
        return (left, right) -> {
            try {
                return operator.applyAsInt(left, right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntConsumer of(UncheckedIntConsumer consumer) {
        return value -> {
            try {
                consumer.accept(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <R> IntFunction<R> of(UncheckedIntFunction<R> function) {
        return value -> {
            try {
                return function.apply(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntPredicate of(UncheckedIntPredicate predicate) {
        return value -> {
            try {
                return predicate.test(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntSupplier of(UncheckedIntSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsInt();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntToDoubleFunction of(UncheckedIntToDoubleFunction function) {
        return value -> {
            try {
                return function.applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntToLongFunction of(UncheckedIntToLongFunction function) {
        return value -> {
            try {
                return function.applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static IntUnaryOperator of(UncheckedIntUnaryOperator operator) {
        return operand -> {
            try {
                return operator.applyAsInt(operand);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongBinaryOperator of(UncheckedLongBinaryOperator operator) {
        return (left, right) -> {
            try {
                return operator.applyAsLong(left, right);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongConsumer of(UncheckedLongConsumer consumer) {
        return value -> {
            try {
                consumer.accept(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <R> LongFunction<R> of(UncheckedLongFunction<R> function) {
        return value -> {
            try {
                return function.apply(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongPredicate of(UncheckedLongPredicate predicate) {
        return value -> {
            try {
                return predicate.test(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongSupplier of(UncheckedLongSupplier supplier) {
        return () -> {
            try {
                return supplier.getAsLong();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongToDoubleFunction of(UncheckedLongToDoubleFunction function) {
        return value -> {
            try {
                return function.applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongToIntFunction of(UncheckedLongToIntFunction function) {
        return value -> {
            try {
                return function.applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static LongUnaryOperator of(UncheckedLongUnaryOperator operator) {
        return operand -> {
            try {
                return operator.applyAsLong(operand);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> ObjDoubleConsumer<T> of(UncheckedObjDoubleConsumer<T> consumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> ObjIntConsumer<T> of(UncheckedObjIntConsumer<T> consumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> ObjLongConsumer<T> of(UncheckedObjLongConsumer<T> consumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Predicate<T> of(UncheckedPredicate<T> predicate) {
        return t -> {
            try {
                return predicate.test(t);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> Supplier<T> of(UncheckedSupplier<T> supplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, U> ToDoubleBiFunction<T, U> of(UncheckedToDoubleBiFunction<T, U> function) {
        return (t, u) -> {
            try {
                return function.applyAsDouble(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> ToDoubleFunction<T> of(UncheckedToDoubleFunction<T> function) {
        return value -> {
            try {
                return function.applyAsDouble(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, U> ToIntBiFunction<T, U> of(UncheckedToIntBiFunction<T, U> function) {
        return (t, u) -> {
            try {
                return function.applyAsInt(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> ToIntFunction<T> of(UncheckedToIntFunction<T> function) {
        return value -> {
            try {
                return function.applyAsInt(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, U> ToLongBiFunction<T, U> of(UncheckedToLongBiFunction<T, U> function) {
        return (t, u) -> {
            try {
                return function.applyAsLong(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T> ToLongFunction<T> of(UncheckedToLongFunction<T> function) {
        return value -> {
            try {
                return function.applyAsLong(value);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }
}
