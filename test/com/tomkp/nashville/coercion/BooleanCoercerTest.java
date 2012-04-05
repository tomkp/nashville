package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.BooleanCoercer;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;



public class BooleanCoercerTest {

    private BooleanCoercer booleanCoercer;

    @Before
    public void setUp() throws Exception {
        booleanCoercer = new BooleanCoercer();
    }

    @Test
    public void emptyStringReturnsFalse() throws Exception {
        assertEquals(false, booleanCoercer.coerce("", null));
    }

    @Test
    public void nullReturnsFalse() throws Exception {
        assertEquals(false, booleanCoercer.coerce(null, null));
    }

    @Test
    public void falseReturnsFalse() throws Exception {
        assertEquals(false, booleanCoercer.coerce("false", null));
    }

    @Test
    public void falseCaptialisedReturnsFalse() throws Exception {
        assertEquals(false, booleanCoercer.coerce("FALSE", null));
    }
    
    @Test
    public void trueReturnsTrue() throws Exception {
        assertEquals(true, booleanCoercer.coerce("true", null));
    }

    @Test
    public void trueCaptialisedReturnsTrue() throws Exception {
        assertEquals(true, booleanCoercer.coerce("TRUE", null));
    }
}
