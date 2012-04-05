package com.tomkp.nashville.coercion;

public class LongCoercer implements Coercer<Long> {

    @Override
    public Long coerce(String value, String format) {
        if (value == null || value.trim().length() == 0) {
            return 0L;
        }
        return Long.parseLong(value);
    }
}
