package com.tomkp.nashville.step;

import junit.framework.TestCase;

public class StepTest extends TestCase {


    private StepInvoker stepInvoker;
    private StepInvokable stepInvokable;

    public StepTest(StepInvokable stepInvokable, StepInvoker stepInvoker) {
        super("test");
        this.stepInvokable = stepInvokable;
        this.stepInvoker = stepInvoker;
    }


    public void test() {
        stepInvoker.invoke(stepInvokable);
        assertTrue(true);
    }


    @Override
    public String getName() {
        return stepInvokable.getLine().getContents();
    }


}
