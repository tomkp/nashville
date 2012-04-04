package com.tomkp.nashville.scanning;

import com.tomkp.nashville.scanning.*;
import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.testdata.annotations.TestFixture1;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnnotationScannerTest {

    private final AnnotationScanner annotationScanner = new AnnotationScanner();


    @Test
    @SuppressWarnings("unchecked")
    public void scanClasses() {
        List<Class> classes = new ArrayList<Class>();
        classes.add(String.class);
        classes.add(TestFixture1.class);
        List<AnnotatedClasses> annotatedClasses = annotationScanner.scanClasses(classes, Fixture.class);
        assertEquals(TestFixture1.class, annotatedClasses.get(0).getClas());
        assertEquals("@com.tomkp.nashville.annotations.Fixture(value=, lifespan=SUITE)", annotatedClasses.get(0).getAnnotation().toString());
    }

    @Test
    @SuppressWarnings("unchecked")
    public void scanMethods() {
        List<AnnotatedMethod> annotatedMethods = annotationScanner.scanMethods(TestFixture1.class, Step.class);

        com.tomkp.nashville.scanning.AnnotatedMethod annotatedMethod1 = annotatedMethods.get(0);
        assertEquals(TestFixture1.class, annotatedMethod1.getClas());
        assertEquals("method1", annotatedMethod1.getMethod().getName());
        assertEquals("@com.tomkp.nashville.annotations.Step(formats=[], value=method 1)", annotatedMethod1.getAnnotation().toString());

        AnnotatedMethod annotatedMethod2 = annotatedMethods.get(1);
        assertEquals(TestFixture1.class, annotatedMethod2.getClas());
        assertEquals("method2", annotatedMethod2.getMethod().getName());
        assertEquals("@com.tomkp.nashville.annotations.Step(formats=[], value=method 2)", annotatedMethod2.getAnnotation().toString());
    }

}
