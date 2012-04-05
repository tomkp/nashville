package com.tomkp.nashville.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Feature {

    private static final Logger LOG = LoggerFactory.getLogger(Feature.class);

    private String name;

    private List<Scenario> scenarios = new ArrayList<Scenario>();

    public Feature(File file) {
        name = file.getName();
        name = "Feature: " + name.replace("_", " ").substring(0, name.lastIndexOf("."));
    }

    public void addScenario(Scenario scenario) {
        LOG.info("add scenario: '{}'", scenario);
        scenarios.add(scenario);
    }

    public List<Scenario> getScenarios() {
        return scenarios;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        LOG.info("set name: '{}'", name);
        this.name = name;
    }
}
