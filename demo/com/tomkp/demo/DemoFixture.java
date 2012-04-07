package com.tomkp.demo;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Lifespan;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.scanning.PackageExplorer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;
import java.util.List;

import static org.junit.Assert.assertEquals;

@Fixture(lifespan = Lifespan.SCENARIO)
public class DemoFixture {

    private static final Logger LOG = LoggerFactory.getLogger(DemoFixture.class);

    private String packageName;
    private List<Class> classes;


    @Step("Given a package '(.*)'")
    public void selectPackage(String packageName) {
        LOG.info("packageName: '{}'", packageName);
        this.packageName = packageName;
    }

    @Step("Given a directory '(.*)'")
    public void selectDirectory(File directory) {
        LOG.info("directory: '{}'", directory);
    }

    @Step("Given a parameter '(.*)'")
    public void withParameter(String parameter) {
        LOG.info("parameter: '{}'", parameter);
    }

    @Step("When I run the coercer")
    public void coercer() {
        LOG.info("coercer");
    }


    @Step("When I run the feature file loader")
    public void fileLoader() {
        LOG.info("file loader");
    }

    @Step("When I run the package explorer")
    public void packageExplorer() {
        LOG.info("run the package explorer");
        classes = new PackageExplorer().getClasses(packageName);
        //assertTrue(false);
        //assertEquals("aaa", "aab");
    }

    @Step("When I scan for '(.*)' annotations")
    public void scanForAnnotations(Class<? extends Annotation> annotation) {
        LOG.info("annotation: '{}'", annotation);
    }

    @Step("Then it should find the classes '(.*)'")
    public void findClasses(List<String> expectedClasses) {
        LOG.info("classes: '{}'", expectedClasses);
        int index = 0;
        for (String aClass : expectedClasses) {
            assertEquals(aClass, classes.get(index++).getSimpleName());
        }
        assertEquals(expectedClasses.size(), classes.size());
    }

    @Step("Then it should find the features '(.*)'")
    public void findFeatures(String features) {
        LOG.info("features: '{}'", features);
    }


}
