package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.TypeCoercion;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertNull;




public class TypeCoercionTest {


    private TypeCoercion coercion;


    @Before
    public void setUp() throws Exception {
        coercion = new TypeCoercion();
    }


    @Test
    public void returnsNullIfNoMatchingCoercerFound() throws Exception {
        Object coerce = coercion.coerce(Thread.class, null, null);
        assertNull(coerce);
    }

}
