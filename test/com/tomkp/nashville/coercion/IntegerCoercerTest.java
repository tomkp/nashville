package com.tomkp.nashville.coercion;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class IntegerCoercerTest {


    private IntegerCoercer coercer;


    @Before
    public void setUp() {
        coercer = new IntegerCoercer();
    }


    @Test
    public void emptyStringDefaultsToZero() {
        assertEquals(0, coercer.coerce("", null), 0);
    }


    @Test
    public void nullDefaultsToZero() {
        assertEquals(0, coercer.coerce(null, null), 0);
    }


    @Test
    public void simpleConversion() {
        assertEquals(17, coercer.coerce("17", null), 0);
    }
}
