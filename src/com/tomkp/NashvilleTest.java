package com.tomkp;

import com.tomkp.nashville.Invokable;
import com.tomkp.nashville.Invoker;
import junit.framework.TestCase;

public class NashvilleTest extends TestCase {


    private Invoker invoker;
    private Invokable invokable;

    public NashvilleTest(Invokable invokable, Invoker invoker) {
        super("test");
        this.invokable = invokable;
        this.invoker = invoker;
    }


    public void test() {
        System.out.println(invokable.getLine());
        invoker.invoke(invokable);
        assertTrue(true);
    }


    @Override
    public String getName() {
        return invokable.getLine().getContents();
    }


}
