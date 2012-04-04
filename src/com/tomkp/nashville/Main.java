package com.tomkp.nashville;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.features.FeatureFileLoader;
import com.tomkp.nashville.scanning.AnnotatedClasses;
import com.tomkp.nashville.scanning.AnnotatedMethod;
import com.tomkp.nashville.scanning.AnnotationScanner;
import com.tomkp.nashville.scanning.PackageExplorer;

import java.io.File;
import java.util.List;

public class Main {


    @SuppressWarnings("unchecked")

    public static void main(String[] args) {

        List<Class> classes = new PackageExplorer().getClasses("com.tomkp.demo");
        AnnotationScanner annotationScanner = new AnnotationScanner();
        List<AnnotatedClasses> annotatedClasses = annotationScanner.scanClasses(classes, Fixture.class);
        for (AnnotatedClasses annotatedClass : annotatedClasses) {
            List<AnnotatedMethod> annotatedMethods = annotationScanner.scanMethods(annotatedClass.getClas(), Step.class);
            for (AnnotatedMethod annotatedMethod : annotatedMethods) {
                System.out.println("annotatedMethod: " + annotatedMethod);
            }
        }

        FeatureFileLoader featureFileLoader = new FeatureFileLoader("feature");
        File featuresDirectory = new File("features");
        List<File> features = featureFileLoader.findFeatures(featuresDirectory);
        for (File feature : features) {
            System.out.println("feature: " + feature);
        }

    }
}
