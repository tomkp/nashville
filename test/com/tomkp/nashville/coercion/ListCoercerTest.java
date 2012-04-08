package com.tomkp.nashville.coercion;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListCoercerTest {


    private final ListCoercer coercer = new ListCoercer();


    @Test
    public void coerceListWithDefaultFormat() {
        List<String> list = coercer.coerce("jim, bob, jane", null);
        assertEquals(list.get(0), "jim");
        assertEquals(list.get(1), "bob");
        assertEquals(list.get(2), "jane");
        assertEquals(list.size(), 3);
    }


    @Test
    public void coerceListWithPipeFormat() {
        List<String> list = coercer.coerce("jim|bob|jane", "\\|");
        assertEquals(list.get(0), "jim");
        assertEquals(list.get(1), "bob");
        assertEquals(list.get(2), "jane");
        assertEquals(list.size(), 3);
    }


    @Test
    public void coerceListWithCommaFormat() {
        List<String> list = coercer.coerce("jim,bob,jane", ",");
        assertEquals(list.get(0), "jim");
        assertEquals(list.get(1), "bob");
        assertEquals(list.get(2), "jane");
        assertEquals(list.size(), 3);
    }

}
