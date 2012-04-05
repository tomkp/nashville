package com.tomkp.nashville.coercion;

public class StringCoercer implements Coercer<String> {

    @Override
    public String coerce(String value, String format) {
        if (value != null && value.length() > 0) {
            return value;
        }
        return null;
    }


}
