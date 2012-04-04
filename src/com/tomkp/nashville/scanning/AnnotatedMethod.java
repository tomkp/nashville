package com.tomkp.nashville.scanning;

import java.lang.reflect.Method;

public class AnnotatedMethod<A> {

    private A annotation;
    private Class clas;
    private Method method;

    public AnnotatedMethod(A annotation, Class clas, Method method) {
        this.annotation = annotation;
        this.clas = clas;
        this.method = method;
    }

    public A getAnnotation() {
        return annotation;
    }

    public Class getClas() {
        return clas;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AnnotatedMethod");
        sb.append("{annotation=").append(annotation);
        sb.append(", clas=").append(clas);
        sb.append(", method=").append(method);
        sb.append('}');
        return sb.toString();
    }
}
