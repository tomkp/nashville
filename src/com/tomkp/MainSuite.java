package com.tomkp;

import com.tomkp.nashville.*;
import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.features.Feature;
import com.tomkp.nashville.features.FeatureParser;
import com.tomkp.nashville.features.Line;
import com.tomkp.nashville.features.Scenario;
import com.tomkp.nashville.utils.RecursiveFileLoader;
import com.tomkp.nashville.scanning.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.List;

public class MainSuite {

    private static final Logger LOG = LoggerFactory.getLogger(MainSuite.class);

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        StepMatcher stepMatcher = new StepMatcher();

        List<Class> classes = new PackageExplorer().getClasses("com.tomkp.demo");
        AnnotationScanner annotationScanner = new AnnotationScanner();
        List<AnnotatedClasses> annotatedClasses = annotationScanner.scanClasses(classes, Fixture.class);
        for (AnnotatedClasses annotatedClass : annotatedClasses) {
            List<AnnotatedMethod> annotatedMethods = annotationScanner.scanMethods(annotatedClass.getClas(), Step.class);
            for (AnnotatedMethod annotatedMethod : annotatedMethods) {
                stepMatcher.store(annotatedMethod);
            }
        }

        Invoker invoker = new Invoker(new StepParametersConverter(), new FixtureInstanceCache());

        RecursiveFileLoader recursiveFileLoader = new RecursiveFileLoader("feature");
        File featuresDirectory = new File("features");
        List<File> features = recursiveFileLoader.findFeatures(featuresDirectory);
        FeatureParser featureParser = new FeatureParser();
        for (File featureFile : features) {
            Feature feature = featureParser.parse(featureFile);
            LOG.info("feature: '{}'", feature);
            List<Scenario> scenarios = feature.getScenarios();
            for (Scenario scenario : scenarios) {
                LOG.info("scenario: '{}'", scenario);
                List<Line> lines = scenario.getLines();
                for (Line line : lines) {
                    LOG.info("line: '{}'", line);

                    Invokable invokable = stepMatcher.match(line);
                    invoker.invoke(invokable);
                }
            }
        }

    }
}
