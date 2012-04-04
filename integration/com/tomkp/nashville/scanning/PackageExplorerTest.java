package com.tomkp.nashville.scanning;

import com.tomkp.nashville.scanning.PackageExplorer;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PackageExplorerTest {


    @Test
    public void findsClassesInPackage() {
        List<Class> classes = new PackageExplorer().getClasses("com.tomkp.testdata.classes");
        assertEquals("TestClass3", classes.get(0).getSimpleName());
        assertEquals("TestClass1", classes.get(1).getSimpleName());
        assertEquals("TestClass2", classes.get(2).getSimpleName());
    }


    @Test
    public void findsClassesInNonExistantPackage() {
        List<Class> classes = new PackageExplorer().getClasses("com.tomkp.testdata.nonexistant");
        assertTrue(classes.isEmpty());
    }

}
