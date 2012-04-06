package com.tomkp.demo;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Lifespan;
import com.tomkp.nashville.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@Fixture(lifespan = Lifespan.SCENARIO)
public class DemoFixture {

    private static final Logger LOG = LoggerFactory.getLogger(DemoFixture.class);

    @Step("Given a package '(.*)'")
    public void selectPackage(String packageName) {
        LOG.info("packageName: '{}'", packageName);
    }

    @Step("Given a directory '(.*)'")
    public void selectDirectory(File directory) {
        LOG.info("directory: '{}'", directory);
    }

    @Step("Given a parameter '(.*)'")
    public void withParameter(String parameter) {
        LOG.info("parameter: '{}'", parameter);
    }

    @Step("When I run the (.*)")
    public void runProcess(String process) {
        LOG.info("process: '{}'", process);
        //assertTrue(false);
        //assertEquals("aaa", "aab");
    }

    @Step("When I scan for '(.*)' annotations")
    public void scanForAnnotations(Class<? extends Annotation> annotation) {
        LOG.info("annotation: '{}'", annotation);
    }

    @Step("Then it should find the classes '(.*)'")
    public void findClasses(String classes) {
        LOG.info("classes: '{}'", classes);
    }

    @Step("Then it should find the features '(.*)'")
    public void findFeatures(String features) {
        LOG.info("features: '{}'", features);
    }



}
