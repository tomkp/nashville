package com.tomkp.demo;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.lang.annotation.Annotation;

@Fixture
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

    @Step("When I run the (.*)")
    public void runProcess(String process) {
        LOG.info("process: '{}'", process);
    }

    @Step("When I scan for '(.*)' annotations")
    public void scanForAnnotations(Class<? extends Annotation> annotation) {
        LOG.info("annotation: '{}'", annotation);
    }

    @Step("Then it should find the classes '(.*)'")
    public void findClasses(String classes) {
        LOG.info("classes: '{}'", classes);
    }


}
