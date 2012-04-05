package com.tomkp.nashville.coercion;

public class BooleanCoercer implements Coercer<Boolean> {

    @Override
    public Boolean coerce(String value, String format) {
        if (value == null || value.length() == 0) {
            return false;
        }
        return Boolean.parseBoolean(value);
    }
}
