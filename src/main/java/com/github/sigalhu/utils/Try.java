package com.github.sigalhu.utils;

import com.github.sigalhu.function.unchecked.*;

import java.util.function.*;

/**
 * @author huxujun
 * @date 2019-04-20
 */
public class Try {

    public static Runnable of(UncheckedRunnable runnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static Runnable of(UncheckedRunnable runnable, Runnable defaultRunnable) {
        return () -> {
            try {
                runnable.run();
            } catch (Exception e) {
                defaultRunnable.run();
            }
        };
    }

    public static <T, U> BiConsumer<T, U> of(UncheckedBiConsumer<T, U> consumer) {
        return (t, u) -> {
            try {
                consumer.accept(t, u);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

    public static <T, U> BiConsumer<T, U> of(UncheckedBiConsumer<T, U> consumer, BiConsumer<T, U> defaultConsumer) {
        return (t, u) -> {
            try {
                consumer.accept(t, u);
            } catch (Exception e) {
                defaultConsumer.accept(t, u);
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

    public static <T, U, R> BiFunction<T, U, R> of(UncheckedBiFunction<T, U, R> function, BiFunction<T, U, R> defaultFunction) {
        return (t, u) -> {
            try {
                return function.apply(t, u);
            } catch (Exception e) {
                return defaultFunction.apply(t, u);
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

    public static <T, U> BiPredicate<T, U> of(UncheckedBiPredicate<T, U> predicate, BiPredicate<T, U> defaultPredicate) {
        return (t, u) -> {
            try {
                return predicate.test(t, u);
            } catch (Exception e) {
                return defaultPredicate.test(t, u);
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

    public static BooleanSupplier of(UncheckedBooleanSupplier supplier, BooleanSupplier defaultSupplier) {
        return () -> {
            try {
                return supplier.getAsBoolean();
            } catch (Exception e) {
                return defaultSupplier.getAsBoolean();
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

    public static <T> Consumer<T> of(UncheckedConsumer<T> consumer, Consumer<T> defaultConsumer) {
        return t -> {
            try {
                consumer.accept(t);
            } catch (Exception e) {
                defaultConsumer.accept(t);
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

    public static DoubleBinaryOperator of(UncheckedDoubleBinaryOperator operator, DoubleBinaryOperator defaultOperator) {
        return (left, right) -> {
            try {
                return operator.applyAsDouble(left, right);
            } catch (Exception e) {
                return defaultOperator.applyAsDouble(left, right);
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

    public static DoubleConsumer of(UncheckedDoubleConsumer consumer, DoubleConsumer defaultConsumer) {
        return value -> {
            try {
                consumer.accept(value);
            } catch (Exception e) {
                defaultConsumer.accept(value);
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

    public static <R> DoubleFunction<R> of(UncheckedDoubleFunction<R> function, DoubleFunction<R> defaultFunction) {
        return value -> {
            try {
                return function.apply(value);
            } catch (Exception e) {
                return defaultFunction.apply(value);
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

    public static DoublePredicate of(UncheckedDoublePredicate predicate, DoublePredicate defaultPredicate) {
        return value -> {
            try {
                return predicate.test(value);
            } catch (Exception e) {
                return defaultPredicate.test(value);
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

    public static DoubleSupplier of(UncheckedDoubleSupplier supplier, DoubleSupplier defaultSupplier) {
        return () -> {
            try {
                return supplier.getAsDouble();
            } catch (Exception e) {
                return defaultSupplier.getAsDouble();
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

    public static DoubleToIntFunction of(UncheckedDoubleToIntFunction function, DoubleToIntFunction defaultFunction) {
        return value -> {
            try {
                return function.applyAsInt(value);
            } catch (Exception e) {
                return defaultFunction.applyAsInt(value);
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

    public static DoubleToLongFunction of(UncheckedDoubleToLongFunction function, DoubleToLongFunction defaultFunction) {
        return value -> {
            try {
                return function.applyAsLong(value);
            } catch (Exception e) {
                return defaultFunction.applyAsLong(value);
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

    public static DoubleUnaryOperator of(UncheckedDoubleUnaryOperator operator, DoubleUnaryOperator defaultOperator) {
        return operand -> {
            try {
                return operator.applyAsDouble(operand);
            } catch (Exception e) {
                return defaultOperator.applyAsDouble(operand);
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

    public static <T, R> Function<T, R> of(UncheckedFunction<T, R> function, Function<T, R> defaultFunction) {
        return t -> {
            try {
                return function.apply(t);
            } catch (Exception e) {
                return defaultFunction.apply(t);
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

    public static IntBinaryOperator of(UncheckedIntBinaryOperator operator, IntBinaryOperator defaultOperator) {
        return (left, right) -> {
            try {
                return operator.applyAsInt(left, right);
            } catch (Exception e) {
                return defaultOperator.applyAsInt(left, right);
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

    public static IntConsumer of(UncheckedIntConsumer consumer, IntConsumer defaultConsumer) {
        return value -> {
            try {
                consumer.accept(value);
            } catch (Exception e) {
                defaultConsumer.accept(value);
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

    public static <R> IntFunction<R> of(UncheckedIntFunction<R> function, IntFunction<R> defaultFunction) {
        return value -> {
            try {
                return function.apply(value);
            } catch (Exception e) {
                return defaultFunction.apply(value);
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

    public static IntPredicate of(UncheckedIntPredicate predicate, IntPredicate defaultPredicate) {
        return value -> {
            try {
                return predicate.test(value);
            } catch (Exception e) {
                return defaultPredicate.test(value);
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

    public static IntSupplier of(UncheckedIntSupplier supplier, IntSupplier defaultSupplier) {
        return () -> {
            try {
                return supplier.getAsInt();
            } catch (Exception e) {
                return defaultSupplier.getAsInt();
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

    public static IntToDoubleFunction of(UncheckedIntToDoubleFunction function, IntToDoubleFunction defaultFunction) {
        return value -> {
            try {
                return function.applyAsDouble(value);
            } catch (Exception e) {
                return defaultFunction.applyAsDouble(value);
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

    public static IntToLongFunction of(UncheckedIntToLongFunction function, IntToLongFunction defaultFunction) {
        return value -> {
            try {
                return function.applyAsLong(value);
            } catch (Exception e) {
                return defaultFunction.applyAsLong(value);
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

    public static IntUnaryOperator of(UncheckedIntUnaryOperator operator, IntUnaryOperator defaultOperator) {
        return operand -> {
            try {
                return operator.applyAsInt(operand);
            } catch (Exception e) {
                return defaultOperator.applyAsInt(operand);
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

    public static LongBinaryOperator of(UncheckedLongBinaryOperator operator, LongBinaryOperator defaultOperator) {
        return (left, right) -> {
            try {
                return operator.applyAsLong(left, right);
            } catch (Exception e) {
                return defaultOperator.applyAsLong(left, right);
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

    public static LongConsumer of(UncheckedLongConsumer consumer, LongConsumer defaultConsumer) {
        return value -> {
            try {
                consumer.accept(value);
            } catch (Exception e) {
                defaultConsumer.accept(value);
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

    public static <R> LongFunction<R> of(UncheckedLongFunction<R> function, LongFunction<R> defaultFunction) {
        return value -> {
            try {
                return function.apply(value);
            } catch (Exception e) {
                return defaultFunction.apply(value);
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

    public static LongPredicate of(UncheckedLongPredicate predicate, LongPredicate defaultPredicate) {
        return value -> {
            try {
                return predicate.test(value);
            } catch (Exception e) {
                return defaultPredicate.test(value);
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

    public static LongSupplier of(UncheckedLongSupplier supplier, LongSupplier defaultSupplier) {
        return () -> {
            try {
                return supplier.getAsLong();
            } catch (Exception e) {
                return defaultSupplier.getAsLong();
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

    public static LongToDoubleFunction of(UncheckedLongToDoubleFunction function, LongToDoubleFunction defaultFunction) {
        return value -> {
            try {
                return function.applyAsDouble(value);
            } catch (Exception e) {
                return defaultFunction.applyAsDouble(value);
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

    public static LongToIntFunction of(UncheckedLongToIntFunction function, LongToIntFunction defaultFunction) {
        return value -> {
            try {
                return function.applyAsInt(value);
            } catch (Exception e) {
                return defaultFunction.applyAsInt(value);
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

    public static LongUnaryOperator of(UncheckedLongUnaryOperator operator, LongUnaryOperator defaultOperator) {
        return operand -> {
            try {
                return operator.applyAsLong(operand);
            } catch (Exception e) {
                return defaultOperator.applyAsLong(operand);
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

    public static <T> ObjDoubleConsumer<T> of(UncheckedObjDoubleConsumer<T> consumer, ObjDoubleConsumer<T> defaultConsumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);
            } catch (Exception e) {
                defaultConsumer.accept(t, value);
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

    public static <T> ObjIntConsumer<T> of(UncheckedObjIntConsumer<T> consumer, ObjIntConsumer<T> defaultConsumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);
            } catch (Exception e) {
                defaultConsumer.accept(t, value);
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

    public static <T> ObjLongConsumer<T> of(UncheckedObjLongConsumer<T> consumer, ObjLongConsumer<T> defaultConsumer) {
        return (t, value) -> {
            try {
                consumer.accept(t, value);
            } catch (Exception e) {
                defaultConsumer.accept(t, value);
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

    public static <T> Predicate<T> of(UncheckedPredicate<T> predicate, Predicate<T> defaultPredicate) {
        return t -> {
            try {
                return predicate.test(t);
            } catch (Exception e) {
                return defaultPredicate.test(t);
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

    public static <T> Supplier<T> of(UncheckedSupplier<T> supplier, Supplier<T> defaultSupplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                return defaultSupplier.get();
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

    public static <T, U> ToDoubleBiFunction<T, U> of(UncheckedToDoubleBiFunction<T, U> function, ToDoubleBiFunction<T, U> defaultFunction) {
        return (t, u) -> {
            try {
                return function.applyAsDouble(t, u);
            } catch (Exception e) {
                return defaultFunction.applyAsDouble(t, u);
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

    public static <T> ToDoubleFunction<T> of(UncheckedToDoubleFunction<T> function, ToDoubleFunction<T> defaultFunction) {
        return value -> {
            try {
                return function.applyAsDouble(value);
            } catch (Exception e) {
                return defaultFunction.applyAsDouble(value);
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

    public static <T, U> ToIntBiFunction<T, U> of(UncheckedToIntBiFunction<T, U> function, ToIntBiFunction<T, U> defaultFunction) {
        return (t, u) -> {
            try {
                return function.applyAsInt(t, u);
            } catch (Exception e) {
                return defaultFunction.applyAsInt(t, u);
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

    public static <T> ToIntFunction<T> of(UncheckedToIntFunction<T> function, ToIntFunction<T> defaultFunction) {
        return value -> {
            try {
                return function.applyAsInt(value);
            } catch (Exception e) {
                return defaultFunction.applyAsInt(value);
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

    public static <T, U> ToLongBiFunction<T, U> of(UncheckedToLongBiFunction<T, U> function, ToLongBiFunction<T, U> defaultFunction) {
        return (t, u) -> {
            try {
                return function.applyAsLong(t, u);
            } catch (Exception e) {
                return defaultFunction.applyAsLong(t, u);
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

    public static <T> ToLongFunction<T> of(UncheckedToLongFunction<T> function, ToLongFunction<T> defaultFunction) {
        return value -> {
            try {
                return function.applyAsLong(value);
            } catch (Exception e) {
                return defaultFunction.applyAsLong(value);
            }
        };
    }
}
