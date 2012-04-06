package com.tomkp;

import com.tomkp.nashville.Invokable;
import com.tomkp.nashville.Invoker;
import junit.framework.TestCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NashvilleTest extends TestCase {

    private static final Logger LOG = LoggerFactory.getLogger(NashvilleTest.class);

    private Invoker invoker;
    private Invokable invokable;

    public NashvilleTest(Invokable invokable, Invoker invoker) {
        super("test");
        this.invokable = invokable;
        this.invoker = invoker;
    }

    public void test() {
        System.out.println(invokable.getLine());
//        try {
            invoker.invoke(invokable);
//        } catch (Exception e) {
//            LOG.warn("error invoking '{}'", invokable.getLine(), e);
//            throw new AssertionError("error invoking '" + invokable.getLine() + "'");
//        }
        assertTrue(true);
    }


    @Override
    public String getName() {
        return invokable.getLine().getContents();
    }



}
