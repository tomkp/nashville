package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.LongCoercer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class LongCoercerTest {


    private LongCoercer coercer;

    
    @Before
    public void setUp() {
        coercer = new LongCoercer();
    }


    @Test
    public void emptyStringDefaultsToZero() {
        assertEquals(0L, coercer.coerce("", null), 0);
    }


    @Test
    public void nullDefaultsToZero() {
        assertEquals(0L, coercer.coerce(null, null), 0);
    }


     @Test
    public void simpleConversion() {
        assertEquals(17L, coercer.coerce("17", null), 0);
    }
}
