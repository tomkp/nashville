package com.tomkp.nashville.features;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FeatureParser {


    private static final Logger LOG = LoggerFactory.getLogger(FeatureParser.class);


    public Feature parse(File file) {
        Feature feature = new Feature(file);
        LOG.info("create feature from file '{}'", file.getName());
        try {

            String contents = FileUtils.readFileToString(file);
            List<String> lines = Arrays.asList(contents.split("\n"));

            int lineNumber = 0;

            Scenario scenario = null;
            for (String line : lines) {

                lineNumber++;
                line = line.trim();
                if (line.contains(Syntax.FEATURE.getValue())) {
                    feature.setName(line);
                }

                if (line.contains(Syntax.SCENARIO.getValue())) {
                    scenario = new Scenario(feature, line);
                    feature.addScenario(scenario);
                } else if (scenario != null) {

                    if (line.matches(Syntax.CUCUMBER_SYNTAX.getValue())) {

                        LOG.info("line: '{}'", line);

                        scenario.addLine(new Line(scenario, lineNumber, line));
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("error reading file '" + file + "'");
        }
        return feature;
    }
}
