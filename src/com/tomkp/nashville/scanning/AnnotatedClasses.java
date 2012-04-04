package com.tomkp.nashville.scanning;


public class AnnotatedClasses<A> {


    private A annotation;
    private Class clas;

    public AnnotatedClasses(A annotation, Class clas) {
        this.annotation = annotation;
        this.clas = clas;
    }

    public A getAnnotation() {
        return annotation;
    }

    public Class getClas() {
        return clas;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("AnnotatedClasses");
        sb.append("{annotation=").append(annotation);
        sb.append(", clas=").append(clas);
        sb.append('}');
        return sb.toString();
    }
}
