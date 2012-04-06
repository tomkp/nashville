package com.tomkp.nashville.coercion;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ClassCoercerTest {


    private ClassCoercer classCoercer;


    @Before
    public void setUp() {
        classCoercer = new ClassCoercer();
    }


    @Test(expected = RuntimeException.class)
    public void nullValueThrowsRuntimeException() {
        classCoercer.coerce(null, null);
    }


    @Test(expected = RuntimeException.class)
    public void emptyStringThrowsRuntimeException() {
        classCoercer.coerce("", null);
    }


    @Test
    public void stringReturnsStringClass() {
        Class clas = classCoercer.coerce("java.lang.String", null);
        assertEquals("java.lang.String", clas.getName());
    }
}
