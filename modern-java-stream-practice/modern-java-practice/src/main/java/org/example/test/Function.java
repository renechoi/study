package org.example.test;

import java.util.Objects;

@FunctionalInterface
interface Function<T, R> {
    R apply(T t);

    default <V> Function<T, V> andThen(Function<? super R, ? extends V> after) {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }
}
