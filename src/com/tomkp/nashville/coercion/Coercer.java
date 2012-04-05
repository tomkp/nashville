package com.tomkp.nashville.coercion;

public interface Coercer<T> {

    T coerce(String value, String format);

}
