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
public class FileSystemFixture {

    private static final Logger LOG = LoggerFactory.getLogger(FileSystemFixture.class);



    @Step("Given a directory '(.*)'")
    public void selectDirectory(File directory) {
        LOG.info("directory: '{}'", directory);
    }


    @Step("When I run the feature file loader")
    public void fileLoader() {
        LOG.info("file loader");
    }


    @Step("Then it should find the features '(.*)'")
    public void findFeatures(String features) {
        LOG.info("features: '{}'", features);
    }


}
