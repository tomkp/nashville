package com.tomkp.nashville.scanning;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationScanner {


    private static final Logger LOG = LoggerFactory.getLogger(AnnotationScanner.class);


    public List<AnnotatedMethod> scanMethods(Class clas, Class<? extends Annotation>... annotationClasses) {
        LOG.info("scan '{}' for annotations: '{}'", clas.getSimpleName(), annotationClasses);
        List<AnnotatedMethod> annotatedMethods = new ArrayList<AnnotatedMethod>();
        for (Method method : clas.getMethods()) {
            for (Class<? extends Annotation> annotationClass : annotationClasses) {
                if (method.isAnnotationPresent(annotationClass)) {
                    Annotation annotation = method.getAnnotation(annotationClass);
                    annotatedMethods.add(new AnnotatedMethod<Annotation>(annotation, clas, method));
                }
            }
        }
        return annotatedMethods;
    }

}
