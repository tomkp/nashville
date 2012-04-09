package com.tomkp.demo;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Lifespan;
import com.tomkp.nashville.annotations.Step;
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
        LOG.info("scan for annotation: '{}'", annotation);
        classes = new PackageExplorer().getClasses(packageName);
        LOG.info("classes: '{}'", classes);
        //annotatedClasses = new AnnotationScanner().scanClasses(classes, annotation);
        //LOG.info("annotated classes: '{}'", annotatedClasses);
    }


    @Step("Then it should find the classes '(.*)'")
    public void findClasses(List<String> expectedClasses) {
        LOG.info("compare expected classes '{}' and actual classes '{}'", expectedClasses, classes);
        int index = 0;
        assertEquals(expectedClasses.size(), classes.size());
        for (String aClass : expectedClasses) {
            assertEquals(aClass, classes.get(index++).getSimpleName());
        }
    }




}
