package com.tomkp.nashville.scanning;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class StepMatcher {


    private static final Logger LOG = LoggerFactory.getLogger(StepMatcher.class);

    private Map<String, AnnotatedMethod> map = new HashMap<String, AnnotatedMethod>();


    public void match(String key) {
        if (map.containsKey(key)) {
        }
    }

    public void store(AnnotatedMethod annotatedMethod) {
        map.put(annotatedMethod.getAnnotation(), annotatedMethod);
    }

}
