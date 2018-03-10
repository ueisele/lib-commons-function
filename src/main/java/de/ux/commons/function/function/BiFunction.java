package de.ux.commons.function.function;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

/**
 * Represents a function that accepts two arguments and produces a result.
 * This is the two-arity specialization of {@link Function}.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #apply(Object, Object)}.
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @param <R> the type of the result of the function
 */
@FunctionalInterface
public interface BiFunction<T, U, R> extends java.util.function.BiFunction<T, U, R> {

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
     */
    @Override
    default <V> BiFunction<T, U, V> andThen(java.util.function.Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u));
    }

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result and the second argument U of this function.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V> the type of output of the {@code after} function, and of the
     *           composed function
     * @param after the function to apply after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     */
    default <V> BiFunction<T, U, V> andThen(java.util.function.BiFunction<? super R, ? super U, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t, U u) -> after.apply(apply(t, u), u);
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
    default BiConsumer<T, U> consume(Consumer<? super R> consumer) {
        Objects.requireNonNull(consumer);
        return (T t, U u) -> consumer.accept(apply(t, u));
    }

}