package de.ux.commons.function.function;

import java.util.Objects;
import java.util.function.Consumer;

/**
 * Represents a supplier of results.
 *
 * <p>There is no requirement that a new or distinct result be returned each
 * time the supplier is invoked.
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #get()}.
 *
 * @param <T> the type of results supplied by this supplier
 */
@FunctionalInterface
public interface Supplier<T> extends java.util.function.Supplier<T> {

    /**
     * Returns a composed supplier that first applies the result of this supplier to
     * the function, and then supplies the result.
     * If evaluation of this supplier or the function throws an exception, it is relayed to
     * the caller of the composed supplier.
     *
     * @param <V> the type of output of the supplier, and of the
     *           composed function
     * @param after the function to which the result of this supplier is applied
     * @return a composed supplier that applies the result of this supplier to the function
     * and returns the result
     * @throws NullPointerException if after is null
     */
    default <V> Supplier<V> andThen(java.util.function.Function<? super T, ? extends V> after) {
        Objects.requireNonNull(after);
        return () -> after.apply(get());
    }

    /**
     * Returns a composed {@link Runnable} that first supplies the result of this supplies,
     * and then consumes the result.
     * If evaluation of this function of the consumer throws an exception,
     * it is relayed to the caller of the composed consumer.
     *
     * @param consumer the consumer of the result of this supplier
     * @return a composed {@link Runnable} that first supplies the result and then
     * consumes it
     * @throws NullPointerException if consumer is null
     */
    default Runnable consume(Consumer<? super T> consumer) {
        Objects.requireNonNull(consumer);
        return () -> consumer.accept(get());
    }

}