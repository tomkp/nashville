package com.tomkp.nashville.scanning;

import com.tomkp.nashville.annotations.Step;
import com.tomkp.testdata.classes.TestFixture1;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class AnnotationScannerTest {

    private final AnnotationScanner annotationScanner = new AnnotationScanner();




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
