package com.tomkp;

import com.tomkp.nashville.*;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.coercion.ParametersConverter;
import com.tomkp.nashville.features.Feature;
import com.tomkp.nashville.features.FeatureParser;
import com.tomkp.nashville.features.Line;
import com.tomkp.nashville.features.Scenario;
import com.tomkp.nashville.step.StepInvokable;
import com.tomkp.nashville.step.StepInvoker;
import com.tomkp.nashville.step.StepMatcher;
import com.tomkp.nashville.step.StepTest;
import com.tomkp.nashville.utils.RecursiveFileLoader;
import com.tomkp.nashville.scanning.*;
import junit.framework.TestSuite;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class NashvilleSuite extends TestSuite {

    private static final Logger LOG = LoggerFactory.getLogger(NashvilleSuite.class);

    @SuppressWarnings("unchecked")

    public static TestSuite suite() {

        TestSuite testSuite = new TestSuite();


        StepMatcher stepMatcher = new StepMatcher();

        List<Class> fixtureClasses = new PackageExplorer().getClasses("com.tomkp.demo", new FixtureFilter());

        AnnotationScanner annotationScanner = new AnnotationScanner();
        for (Class fixtureClass : fixtureClasses) {
            List<AnnotatedMethod> annotatedSteps = annotationScanner.scanMethods(fixtureClass, Step.class);
            for (AnnotatedMethod annotatedStep : annotatedSteps) {
                stepMatcher.store(annotatedStep);
            }
        }


        StepInvoker stepInvoker = new StepInvoker(new ParametersConverter(), new FixtureInstanceCache());

        RecursiveFileLoader recursiveFileLoader = new RecursiveFileLoader("feature");
        File featuresDirectory = new File("features");
        List<File> features = recursiveFileLoader.findFeatures(featuresDirectory);
        FeatureParser featureParser = new FeatureParser();
        for (File featureFile : features) {
            Feature feature = featureParser.parse(featureFile);

            LOG.info("feature: '{}'", feature);
            TestSuite featureSuite = new TestSuite();
            featureSuite.setName(feature.getName());
            testSuite.addTest(featureSuite);

            List<Scenario> scenarios = feature.getScenarios();
            for (Scenario scenario : scenarios) {

                LOG.info("scenario: '{}'", scenario);
                TestSuite scenarioSuite = new TestSuite();
                scenarioSuite.setName(scenario.getName());
                featureSuite.addTest(scenarioSuite);

                List<Line> lines = scenario.getLines();
                for (Line line : lines) {
                    LOG.info("line: '{}'", line);

                    StepInvokable stepInvokable = stepMatcher.match(line);
                    StepTest stepTest = new StepTest(stepInvokable, stepInvoker);
                    LOG.info("add new test: '{}'", stepTest.getName());
                    scenarioSuite.addTest(stepTest);
                }
            }
        }

        return testSuite;

    }


}
