package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.DoubleCoercer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class DoubleCoercerTest {


    private DoubleCoercer coercer;


    @Before
    public void setUp() throws Exception {
        coercer = new DoubleCoercer();
    }


    @Test
    public void emptyStringDefaultsToZero() throws Exception {
        assertEquals(0D, coercer.coerce("", null), 0);
    }


    @Test
    public void nullDefaultsToZero() throws Exception {
        assertEquals(0D, coercer.coerce(null, null), 0);
    }


     @Test
    public void simpleConversion() throws Exception {
        assertEquals(17D, coercer.coerce("17", null), 0);
    }
}
