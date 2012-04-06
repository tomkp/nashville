package com.tomkp.nashville.coercion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class ListCoercer implements Coercer<List> {


    private static final Logger LOG = LoggerFactory.getLogger(ListCoercer.class);

    @Override
    public List<String> coerce(String value, String format) {
        LOG.info("coerce '{}' to a list using format '{}'", value, format);
        if (format == null) {
            format = ", ";
        }
        String[] split = value.split(format);
        LOG.info("'{}' split into '{}' items", value, split.length);
        return Arrays.asList(split);
    }
}
