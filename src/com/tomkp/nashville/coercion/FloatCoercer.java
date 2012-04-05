package com.tomkp.nashville.coercion;

public class FloatCoercer implements Coercer<Float> {

    @Override
    public Float coerce(String value, String format) {
        if (value == null || value.trim().length() == 0) {
            return 0F;
        }
        return Float.parseFloat(value);
    }
}
