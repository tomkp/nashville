package com.tomkp.nashville.coercion;

import org.junit.Test;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ParametersConverterTest {


    private final ParametersConverter converter = new ParametersConverter();

    @Test
    public void convertStrings() {
        List<Object> parameters = converter.convertParameters(new Class[]{String.class, String.class}, new String[]{}, Arrays.asList("first", "second"));
        assertEquals(parameters.get(0).getClass(), String.class);
        assertEquals(parameters.get(1).getClass(), String.class);
    }


    @Test
    public void convertStringsAndIntegers() {
        List<Object> parameters = converter.convertParameters(new Class[]{String.class, Integer.class}, new String[]{}, Arrays.asList("first", "45"));
        assertEquals(parameters.get(0).getClass(), String.class);
        assertEquals(parameters.get(1).getClass(), Integer.class);
    }

    @Test
    public void convertDates() {
        List<Object> parameters = converter.convertParameters(new Class[]{Date.class, Date.class}, new String[]{"dd/MM/yyyy", "dd MMM yy"}, Arrays.asList("13/04/1987", "3 Jun 10"));
        assertEquals(parameters.get(0).getClass(), Date.class);
        assertEquals(parameters.get(1).getClass(), Date.class);
    }


    @Test
    public void convertClass() {
        List<Object> parameters = converter.convertParameters(new Class[]{Class.class}, new String[]{}, Arrays.asList("java.lang.Boolean"));
        assertEquals(parameters.get(0), Boolean.class);
    }


}
