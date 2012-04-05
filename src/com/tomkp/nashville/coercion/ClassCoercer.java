package com.tomkp.nashville.coercion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClassCoercer implements Coercer<Class> {

    private static final Logger LOG = LoggerFactory.getLogger(ClassCoercer.class);

    @Override
    public Class coerce(String value, String format) {
        LOG.info("coerce '{}'", value);
        if (value != null && !value.isEmpty()) {
            try {
                return Class.forName(value);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException("unable to coerce to Class", e);
            }
        }
        return null;
    }


}
