package com.tomkp.nashville.scanning;

import com.tomkp.nashville.annotations.Fixture;

public class FixtureFilter implements ClassFilter {

    @Override
    public boolean filter(Class clas) {
        return clas.isAnnotationPresent(Fixture.class);
    }

}
