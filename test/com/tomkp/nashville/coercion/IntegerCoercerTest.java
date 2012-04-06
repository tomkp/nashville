package com.tomkp.nashville.coercion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IntegerCoercerTest {


    private IntegerCoercer coercer;


    @Before
    public void setUp() throws Exception {
        coercer = new IntegerCoercer();
    }


    @Test
    public void emptyStringDefaultsToZero() throws Exception {
        assertEquals(0, coercer.coerce("", null), 0);
    }


    @Test
    public void nullDefaultsToZero() throws Exception {
        assertEquals(0, coercer.coerce(null, null), 0);
    }


    @Test
    public void simpleConversion() throws Exception {
        assertEquals(17, coercer.coerce("17", null), 0);
    }
}
