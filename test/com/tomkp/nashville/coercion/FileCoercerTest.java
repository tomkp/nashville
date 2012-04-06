package com.tomkp.nashville.coercion;

import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class FileCoercerTest {

    private FileCoercer fileCoercer;


    @Before
    public void setUp() {
        fileCoercer = new FileCoercer();
    }


    @Test(expected = RuntimeException.class)
    public void nullValueThrowsRuntimeException() {
        fileCoercer.coerce(null, null);
    }


    @Test(expected = RuntimeException.class)
    public void emptyStringThrowsRuntimeException() {
        fileCoercer.coerce("", null);
    }


    @Test
    public void coercesFile() {
        File file = fileCoercer.coerce("test/com/tomkp/nashville/coercion/FileCoercerTest.java", null);
        assertTrue(file.exists());
        assertEquals("FileCoercerTest.java", file.getName());
    }
}
