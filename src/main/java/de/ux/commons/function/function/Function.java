package de.ux.commons.function.function;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents a function that accepts one argument and produces a result.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object)}.
 *
 * @param <T> the type of the input to the function
 * @param <R> the type of the result of the function
 *
 */
@FunctionalInterface
public interface Function<T, R> extends java.util.function.Function<T, R> {

    /**
     * Returns a composed function that first applies the {@code before}
     * function to its input, and then applies this function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of input to the {@code before} function, and to the
     *           composed function
     * @param before the function to apply before this function is applied
     * @return a composed function that first applies the {@code before}
     * function and then applies this function
     * @throws NullPointerException if before is null
     *
     * @see #andThen
     */
    @Override
    default <V> Function<V, R> compose(java.util.function.Function<? super V, ? extends T> before) {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     *
     * @see #compose
     */
    @Override
    default <V> Function<T, V> andThen(java.util.function.Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    /**
     * Returns a composed consumer that first applies this function to
     * its input, and then consumes the result.
     * If evaluation of this function of the consumer throws an exception,
     * it is relayed to the caller of the composed consumer.
     *
     * @param consumer the consumer of the result of this function
     * @return a composed consumer that first applies this functions and then
     * consumes it
     * @throws NullPointerException if consumer is null
     */
    default Consumer<T> consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (T t) -> consumer.accept(apply(t));
    }

    /**
     * Returns a function that always returns its input argument.
     *
     * @param <T> the type of the input and output objects to the function
     * @return a function that always returns its input argument
     */
    static <T> Function<T, T> identity() {
        return t -> t;
    }
}