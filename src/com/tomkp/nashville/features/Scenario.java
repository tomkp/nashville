package com.tomkp.nashville.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Scenario {

    private static final Logger LOG = LoggerFactory.getLogger(Scenario.class);

    private File file;
    private String name;
    private List<Line> lines = new ArrayList<Line>();


    public Scenario(File file, String name) {
        this.file = file;
        this.name = name;
    }


    public File getFile() {
        return file;
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
}
