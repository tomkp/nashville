package com.tomkp.nashville.coercion;

import com.tomkp.nashville.coercion.DateCoercer;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class DateCoercerTest {

    private static final SimpleDateFormat DF = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat SINGLE_YEAR_FORMAT = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat("dd/MM/yyyy");


    private DateCoercer dateCoercer;

    @Before
    public void setUp() throws Exception {
        dateCoercer = new DateCoercer();
    }

    @Test
    public void nullReturnsNull() throws Exception {
        Date date = dateCoercer.coerce(null, null);
        assertNull(date);
    }

    @Test
    public void emptyStringReturnsNull() throws Exception {
        Date date = dateCoercer.coerce("", null);
        assertNull(date);
    }

    @Test
    public void invalidFormatReturnsNull() throws Exception {
        Date date = dateCoercer.coerce("24/06/2011", "this date format is invalid");
        assertNull(date);
    }

    @Test
    public void invalidDateReturnsNull() throws Exception {
        Date date = dateCoercer.coerce("this date is invalid", "dd/MM/yyyy");
        assertNull(date);
    }

    @Test
    public void defaultFormatIsIso8601() throws Exception {
        Date date = dateCoercer.coerce("2008-09-01T13:45:51Z", null);
        assertEquals(DF.parse("01/09/2008 13:45:51"), date);
    }

    @Test
    public void singleYearFormatter() throws Exception {
        Date date = dateCoercer.coerce("2003", null);
        assertEquals(SINGLE_YEAR_FORMAT.parse("2003"), date);
    }

    @Test
    public void dayMonthYearFormatter() throws Exception {
        Date date = dateCoercer.coerce("08/06/2006", null);
        Date expected = DAY_MONTH_YEAR_FORMAT.parse("08/06/2006");
        assertEquals(expected, date);
    }
}
