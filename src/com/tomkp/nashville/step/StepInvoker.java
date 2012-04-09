package com.tomkp.nashville.step;

import com.tomkp.nashville.FixtureInstanceCache;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.coercion.ParametersConverter;
import com.tomkp.nashville.features.Line;
import com.tomkp.nashville.scanning.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class StepInvoker {

    private static final Logger LOG = LoggerFactory.getLogger(StepInvoker.class);


    private ParametersConverter parametersConverter;
    private FixtureInstanceCache instanceCache;


    public StepInvoker(ParametersConverter parametersConverter, FixtureInstanceCache instanceCache) {
        this.parametersConverter = parametersConverter;
        this.instanceCache = instanceCache;
    }


    public void invoke(StepInvokable stepInvokable) {
        AnnotatedMethod<Step> annotatedMethod = stepInvokable.getAnnotatedMethod();
        Line line = stepInvokable.getLine();
        if (annotatedMethod != null) {
            List<String> methodParameters = stepInvokable.getMethodParameters();
            LOG.info("methodParameters: '{}'", methodParameters);
            Method method = annotatedMethod.getMethod();
            Step step = annotatedMethod.getAnnotation();
            String[] formats = step.formats();
            Class<?>[] parameterTypes = method.getParameterTypes();
            List<Object> convertedParameters = parametersConverter.convertParameters(parameterTypes, formats, methodParameters);
            LOG.info("converted convertedParameters: '{}'", convertedParameters);

            try {
                Object instance = instanceCache.getInstance(line, annotatedMethod.getClas());
                System.out.println();
                System.out.println(stepInvokable.getLine());
                if (convertedParameters.isEmpty()) {
                    method.invoke(instance);
                } else {
                    method.invoke(instance, convertedParameters.toArray());
                }
            } catch (InvocationTargetException e) {
                if (e.getTargetException() instanceof AssertionError) {
                    throw (AssertionError) e.getTargetException();
                }
                throw new AssertionError(e.getTargetException());
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
