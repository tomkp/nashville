package com.tomkp.nashville.coercion;

import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;

public class ListCoercer implements Coercer<List> {


    private static final Logger LOG = Logger.getLogger(ListCoercer.class);


    @Override
    public List<String> coerce(String value, String format) {
        if (LOG.isDebugEnabled()) LOG.debug("value: '" + value + "', format: '" + format + "'");

        if (format == null) {
            format = ", ";
        }
        String[] split = value.split(format);
        if (LOG.isDebugEnabled()) LOG.debug("split into: '" + split.length + "' items");
        return Arrays.asList(split);
    }
}
