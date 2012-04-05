package com.tomkp.nashville.features;

public enum Syntax {

    FEATURE("Feature:"),
    SCENARIO("Scenario:"),
    CUCUMBER_SYNTAX("^(Given.*|And.*|Then.*|When.*|But.*)")
    ;


    private String value;


    private Syntax(String value) {
        this.value = value;
    }


    public String getValue() {
        return value;
    }
}
