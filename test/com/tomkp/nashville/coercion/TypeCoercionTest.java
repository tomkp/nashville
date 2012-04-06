package com.tomkp.nashville.coercion;

import org.junit.Test;


public class TypeCoercionTest {


    @Test(expected = RuntimeException.class)
    public void throwsRuntimeExceptionIfNoMatchingCoercerFound() throws Exception {
        TypeCoercion.coerce(Thread.class, null, null);
    }

}
