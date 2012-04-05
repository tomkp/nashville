package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.FloatCoercer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class FloatCoercerTest {


    private FloatCoercer coercer;


    @Before
    public void setUp() throws Exception {
        coercer = new FloatCoercer();
    }
    

    @Test
    public void emptyStringDefaultsToZero() throws Exception {
        assertEquals(0F, coercer.coerce("", null), 0);
    }

    
    @Test
    public void nullDefaultsToZero() throws Exception {
        assertEquals(0F, coercer.coerce(null, null), 0);
    }


     @Test
    public void simpleConversion() throws Exception {
        assertEquals(17F, coercer.coerce("17", null), 0);
    }
}
