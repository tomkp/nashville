package com.tomkp.nashville.coercion;

public class DoubleCoercer implements Coercer<Double> {

    @Override
    public Double coerce(String value, String format) {
        if (value == null || value.trim().length() == 0) {
            return 0D;
        }
        return Double.parseDouble(value);
    }
}
