package com.tomkp.nashville.coercion;

import org.apache.log4j.Logger;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TypeCoercion {

    private static final Logger LOG = Logger.getLogger(TypeCoercion.class);

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
        if (LOG.isDebugEnabled())
            LOG.debug("coerce '" + value + "' to a '" + clazz.getSimpleName() + "' with format: '" + format + "'");
        Coercer coercer = coercers.get(clazz);
        Object coercedValue = null;
        if (coercer != null) {
            coercedValue = coercer.coerce(value, format);
            if (LOG.isDebugEnabled()) LOG.debug("coerced value: '" + coercedValue + "'");
        } else {
            if (LOG.isDebugEnabled()) LOG.debug("no coercer found for '" + clazz.getName() + "'");
        }
        return coercedValue;
    }


}
