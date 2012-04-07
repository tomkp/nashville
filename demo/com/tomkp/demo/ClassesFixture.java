package com.tomkp.demo;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Lifespan;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.scanning.AnnotatedClasses;
import com.tomkp.nashville.scanning.AnnotationScanner;
import com.tomkp.nashville.scanning.PackageExplorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Fixture(lifespan = Lifespan.SCENARIO)
public class ClassesFixture {

    private static final Logger LOG = LoggerFactory.getLogger(ClassesFixture.class);

    private String packageName;
    private List<Class> classes;
    private List<AnnotatedClasses> annotatedClasses;


    @Step("Given a package '(.*)'")
    public void selectPackage(String packageName) {
        LOG.info("packageName: '{}'", packageName);
        this.packageName = packageName;
    }


    @Step("When I run the package explorer")
    public void packageExplorer() {
        LOG.info("run the package explorer");
        classes = new PackageExplorer().getClasses(packageName);
        LOG.info("classes: '{}'", classes);
    }


    @Step("When I scan for '(.*)' annotations")
    public void scanForAnnotations(Class<? extends Annotation> annotation) {
        LOG.info("annotation: '{}'", annotation);
        classes = new PackageExplorer().getClasses(packageName);
        annotatedClasses = new AnnotationScanner().scanClasses(classes, annotation);
    }


    @Step("Then it should find the classes '(.*)'")
    public void findClasses(List<String> expectedClasses) {
        LOG.info("compare expected classes '{}' and actual classes '{}'", expectedClasses, classes);
        int index = 0;
        for (String aClass : expectedClasses) {
            assertEquals(aClass, classes.get(index++).getSimpleName());
        }
        assertEquals(expectedClasses.size(), classes.size());
    }


    @Step("Then it should find the annotated classes '(.*)'")
    public void findAnnotatedClasses(List<String> expectedClasses) {
        LOG.info("compare expected classes '{}' and actual annotated classes '{}'", expectedClasses, annotatedClasses);
        try {
            int index = 0;
            for (String aClass : expectedClasses) {
                assertEquals(aClass, annotatedClasses.get(index++).getClas().getSimpleName());
            }
            assertEquals(expectedClasses.size(), classes.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
