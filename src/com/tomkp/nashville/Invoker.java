package com.tomkp.nashville;

import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.features.Line;
import com.tomkp.nashville.scanning.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class Invoker {

    private static final Logger LOG = LoggerFactory.getLogger(Invoker.class);


    private StepParametersConverter stepParametersConverter;
    private FixtureInstanceCache instanceCache;


    public Invoker(StepParametersConverter stepParametersConverter, FixtureInstanceCache instanceCache) {
        this.stepParametersConverter = stepParametersConverter;
        this.instanceCache = instanceCache;
    }


    public void invoke(Invokable invokable) {
        AnnotatedMethod<Step> annotatedMethod = invokable.getAnnotatedMethod();
        Line line = invokable.getLine();
        if (annotatedMethod != null) {
            List<String> methodParameters = invokable.getMethodParameters();
            LOG.info("methodParameters: '{}'", methodParameters);
            List<Object> convertedParameters = stepParametersConverter.convertParameters(annotatedMethod, methodParameters);
            LOG.info("converted convertedParameters: '{}'", convertedParameters);

            Method method = annotatedMethod.getMethod();
            try {
                Object instance = instanceCache.getInstance(line, annotatedMethod);
                if (convertedParameters.isEmpty()) {
                    method.invoke(instance);
                } else {
                    method.invoke(instance, convertedParameters.toArray());
                }
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof AssertionError) {
                    throw (AssertionError) e.getTargetException();
                } else {
                    LOG.warn("unable to invoke '{}'", new Object[]{method}, e);
                }
                throw new AssertionError("unable to invoke method '" + method + "'");
            } catch (Exception e) {
                LOG.warn("unable to invoke '{}'", new Object[]{method}, e);
                throw new AssertionError("unable to invoke '" + method + "'");
            }
        } else {
            LOG.warn("no annotated method found for '{}'", line);
            throw new AssertionError("no annotated method found for '" + line + "'");
        }
    }
}
