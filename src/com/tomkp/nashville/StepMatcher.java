package com.tomkp.nashville;



import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.features.Line;
import com.tomkp.nashville.scanning.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepMatcher {


    private static final Logger LOG = LoggerFactory.getLogger(StepMatcher.class);


    private Map<Pattern, AnnotatedMethod<Step>> map = new HashMap<Pattern, AnnotatedMethod<Step>>();



    public Invokable match(Line line) {
        LOG.info("match line: '{}'", line);
        String contents = line.getContents();
        AnnotatedMethod<Step> annotatedMethod = null;
        List<String> methodParameters = new ArrayList<String>();
        for (Pattern pattern : map.keySet()) {
            LOG.info("match '{}' to pattern '{}'", contents, pattern);
            Matcher matcher = pattern.matcher(contents);
            if (matcher.matches()) {
                annotatedMethod = map.get(pattern);
                LOG.info("'{}' matches '{}'", annotatedMethod, contents);

                int count = matcher.groupCount();
                for (int i = 0; i <= count; i++) {
                    String group = matcher.group(i);
                    methodParameters.add(group);
                }
                break;
            }
        }

        return new Invokable(annotatedMethod, methodParameters, line);
    }




    public void store(AnnotatedMethod<Step> annotatedMethod) {
        String value = annotatedMethod.getAnnotation().value();
        map.put(Pattern.compile(value), annotatedMethod);
    }

}
