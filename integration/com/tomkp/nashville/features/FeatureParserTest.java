package com.tomkp.nashville.features;

import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FeatureParserTest {

    private final FeatureParser featureParser = new FeatureParser();


    @Test
    public void parseFile() {
        File file = new File("testdata/features/test-feature1.feature");

        Feature feature = featureParser.parse(file);

        List<Scenario> scenarios = feature.getScenarios();
        Scenario scenario = scenarios.get(0);
        List<Line> lines = scenario.getLines();

        assertEquals("Feature: This is a test feature", feature.getName());

        assertEquals("Scenario: My first scenario", scenario.getName());
        assertEquals(file.getAbsolutePath(), scenario.getFilePath());

        Line line1 = lines.get(0);
        assertEquals("Given an initial state", line1.getContents());
        assertEquals(7, line1.getNumber());
        assertEquals(scenario, line1.getScenario());

        Line line2 = lines.get(1);
        assertEquals("Then I perform an action", line2.getContents());
        assertEquals(8, line2.getNumber());
        assertEquals(scenario, line2.getScenario());

        Line line3 = lines.get(2);
        assertEquals("And I perform a different action", line3.getContents());
        assertEquals(9, line3.getNumber());
        assertEquals(scenario, line3.getScenario());

        Line line4 = lines.get(3);
        assertEquals("Then I should assert", line4.getContents());
        assertEquals(10, line4.getNumber());
        assertEquals(scenario, line4.getScenario());

        assertEquals(4, lines.size());
    }




    @Test(expected = RuntimeException.class)
    public void parsingNonExistentFileThrowsRuntimeException() {
        File file = new File("testdata/features/non-existent.xxx");
        featureParser.parse(file);
    }

}
