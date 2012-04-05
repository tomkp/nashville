package com.tomkp.nashville;

import com.tomkp.nashville.annotations.Fixture;
import com.tomkp.nashville.annotations.Lifespan;
import com.tomkp.nashville.annotations.Step;
import com.tomkp.nashville.scanning.AnnotatedMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

public class FixtureInstanceCache {


    private static final Logger LOG = LoggerFactory.getLogger(FixtureInstanceCache.class);


    private Map<Class, Object> instances = new HashMap<Class, Object>();


    @SuppressWarnings("unchecked")
    public Object getInstance(AnnotatedMethod<Step> annotatedMethod) throws InstantiationException, IllegalAccessException {
        Class clas = annotatedMethod.getClas();
        Fixture annotation = (Fixture)clas.getAnnotation(Fixture.class);
        LOG.info("annotation: '{}'", annotation);

        Lifespan lifespan = annotation.lifespan();
        Object instance = null;
        if (lifespan == Lifespan.SUITE) {
            if (instances.containsKey(clas)) {
                instance = instances.get(clas);
                LOG.info("use instance '{}' from cache", instance);
            } else {
                instance = clas.newInstance();
                LOG.info("store instance '{}' in cache", instance);
                instances.put(clas, instance);
            }
        } else {
            LOG.info("create new instance of '{}'", clas);
            instance = clas.newInstance();
        }
        return instance;
    }

}
