package com.tomkp.nashville.coercion;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TypeCoercionTest {


    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionIfNoMatchingCoercerFound() {
        TypeCoercion.coerce(Thread.class, null, null);
    }


    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionWhenCoercionFails() {
        TypeCoercion.coerce(Integer.class, "not an Integer", null);
    }


    @Test
    public void coercesValue() {
        Integer coerced = (Integer)TypeCoercion.coerce(Integer.class, "13", null);
        assertEquals(coerced.intValue(), 13);
    }

}
