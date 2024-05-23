package com.example.ecommerce.mappers;

public interface Mapper<T, V> {
    V mapTo(T t);

    T mapFrom(V v);
}
