package com.tomkp.nashville.coercion;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeCoercion {

    private static final Logger LOG = LoggerFactory.getLogger(TypeCoercion.class);

    private static Map<Class, Coercer> coercers = new HashMap<Class, Coercer>();


    static {
        coercers.put(String.class, new StringCoercer());
        coercers.put(Integer.class, new IntegerCoercer());
        coercers.put(Boolean.class, new BooleanCoercer());
        coercers.put(Long.class, new LongCoercer());
        coercers.put(Double.class, new DoubleCoercer());
        coercers.put(Float.class, new FloatCoercer());
        coercers.put(Date.class, new DateCoercer());
        coercers.put(List.class, new ListCoercer());
        coercers.put(Class.class, new ClassCoercer());
        coercers.put(File.class, new FileCoercer());
    }

    public static Object coerce(Class clazz, String value, String format) {
        LOG.info("coerce '{}' to a '{}' with format: '{}'", new Object[] {value, clazz.getSimpleName(), format});
        Coercer coercer = coercers.get(clazz);
        Object coercedValue = null;
        if (coercer != null) {
            coercedValue = coercer.coerce(value, format);
            LOG.info("coerced value: '{}'", coercedValue);
        } else {
            LOG.warn("no coercer found for '{}'", clazz.getName());
            throw new RuntimeException("no coercer found for '" + clazz.getName() + "'");
        }
        return coercedValue;
    }


}
