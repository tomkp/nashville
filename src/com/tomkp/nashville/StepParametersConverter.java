package com.tomkp.nashville;

import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.coercion.TypeCoercion;
import com.tomkp.nashville.scanning.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class StepParametersConverter {


    private static final Logger LOG = LoggerFactory.getLogger(StepParametersConverter.class);


    public List<Object> convertParameters(AnnotatedMethod<Step> annotatedMethod, List<String> parameters) {
        Method method = annotatedMethod.getMethod();
        Step step = annotatedMethod.getAnnotation();
        String[] formats = step.formats();
        Class<?>[] classes = method.getParameterTypes();
        List<String> subList = parameters.subList(1, parameters.size());
        List<Object> convertedParameters = new ArrayList<Object>();

        for (int i = 0; i < classes.length; i++) {

            Class<?> clas = classes[i];

            String param = subList.get(i);
            String format = null;

            if (i < formats.length) {
                format = formats[i];
                LOG.info("format: '{}'", format);
            }

            try {
                Object coerced = TypeCoercion.coerce(clas, param, format);
                LOG.info("coerced: '{}' to a  '{}'", coerced, coerced.getClass().getSimpleName());
                convertedParameters.add(coerced);
            } catch (Exception e) {
                throw new AssertionError("error coercing parameter '" + param + "'");
            }
        }

        return convertedParameters;
    }
}
