package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.StringCoercer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class StringCoercerTest {


    private StringCoercer coercer;


    @Before
    public void setUp() throws Exception {
        coercer = new StringCoercer();
    }


    @Test
    public void emptyStringReturnsEmptyString() throws Exception {
        assertNull(coercer.coerce("", null));
    }


    @Test
    public void nullDefaultsToNull() throws Exception {
        assertNull(coercer.coerce(null, null));
    }


    @Test
    public void simpleConversion() throws Exception {
        assertEquals("hello", coercer.coerce("hello", null));
    }
}
