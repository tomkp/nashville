package com.tomkp.nashville.annotations;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Fixture {

    String value() default "";

    Lifespan lifespan() default Lifespan.SUITE;

}