package com.tomkp.evented;

import org.apache.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AnnotationScanner {

    private static final Logger LOG = Logger.getLogger(AnnotationScanner.class);


    private List<AnnotationListener> listeners = new ArrayList<AnnotationListener>();


    public void scanClasses(List<Class> classes, Class<?>... annotationClasses) throws Exception {
        for (Class clas : classes) {
            for (Class<?> annotationClass : annotationClasses) {
                if (clas.isAnnotationPresent(annotationClass)) {
                    LOG.info("'" + clas + "' is annotated as a '" + annotationClass.getSimpleName() + "'");
                    @SuppressWarnings("unchecked")
                    A annotation = ((Class<A>) clas).getAnnotation(annotationClass);
                    fireEvent(new AnnotatedClassEvent<A>(annotation, clas));
                }
            }
        }
    }


    public <A extends Annotation> void scanMethods(Class clas, Class<A>... annotationClasses) {
        LOG.info("scan '" + clas.getSimpleName() + "' for annotations: '" + Arrays.asList(annotationClasses) + "'");
        for (Method method : clas.getMethods()) {
            for (Class<A> annotationClass : annotationClasses) {
                if (method.isAnnotationPresent(annotationClass)) {
                    A annotation = method.getAnnotation(annotationClass);
                    fireEvent(new AnnotatedMethodEvent<A>(annotation, clas, method));
                }
            }
        }
    }


    public void addAnnotationListener(AnnotationListener listener) {
        listeners.add(listener);
    }


    public void fireEvent(AnnotatedMethodEvent event) {
        for (AnnotationListener listener : listeners) {
            listener.annotatedMethod(event);
        }
    }

    private void fireEvent(AnnotatedClassEvent event) {
        for (AnnotationListener listener : listeners) {
            listener.annotatedClass(event);
        }
    }


}
