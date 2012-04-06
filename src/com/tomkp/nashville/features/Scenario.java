package com.tomkp.nashville.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private static final Logger LOG = LoggerFactory.getLogger(Scenario.class);

    private Feature feature;
    private String name;
    private List<Line> lines = new ArrayList<Line>();


    public Scenario(Feature feature, String name) {
        this.feature = feature;
        this.name = name;
    }


    public Feature getFeature() {
        return feature;
    }


    public String getName() {
        return name;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void addLine(Line line) {
        LOG.info("add line: '{}'", line);
        lines.add(line);
    }

    public String getFilePath() {
        return feature.getFilePath();
    }
}
