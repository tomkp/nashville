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
public class CoercionFixture {

    private static final Logger LOG = LoggerFactory.getLogger(CoercionFixture.class);


    @Step("Given a parameter '(.*)'")
    public void withParameter(String parameter) {
        LOG.info("parameter: '{}'", parameter);
    }

    @Step("When I run the coercer")
    public void coercer() {
        LOG.info("coercer");
    }




}
