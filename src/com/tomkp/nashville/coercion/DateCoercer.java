package com.tomkp.nashville.coercion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DateCoercer implements Coercer<Date> {

    private static final Logger LOG = LoggerFactory.getLogger(DateCoercer.class);

    private static final SimpleDateFormat DEFAULT_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    private static final SimpleDateFormat DAY_MONTH_YEAR_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
    private static final SimpleDateFormat SINGLE_YEAR_FORMAT = new SimpleDateFormat("yyyy");

    private static final List<SimpleDateFormat> DEFAULT_DATE_FORMATS = Arrays.asList(DEFAULT_FORMAT, DAY_MONTH_YEAR_FORMAT, SINGLE_YEAR_FORMAT);

    @Override
    public Date coerce(String value, String format) {
        List<SimpleDateFormat> simpleDateFormats = DEFAULT_DATE_FORMATS;
        if (format != null && format.length() > 0) {
            try {
                simpleDateFormats = Arrays.asList(new SimpleDateFormat(format));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("error creating date format '" + format + "'", e);
            }
        }
        for (SimpleDateFormat dateFormat : simpleDateFormats) {
            try {
                LOG.info("parse date with format: '{}'", dateFormat.toPattern());
                return dateFormat.parse(value);
            } catch (Exception ignored) {
            }
        }
        throw new RuntimeException("error parsing date '" + value + "' with format '" + format + "'");
    }


}
