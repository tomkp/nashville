package com.tomkp.nashville.step;

import junit.framework.TestCase;

public class StepTest extends TestCase {


    private Invoker invoker;
    private Invokable invokable;

    public StepTest(Invokable invokable, Invoker invoker) {
        super("test");
        this.invokable = invokable;
        this.invoker = invoker;
    }


    public void test() {
        invoker.invoke(invokable);
        assertTrue(true);
    }


    @Override
    public String getName() {
        return invokable.getLine().getContents();
    }


}
