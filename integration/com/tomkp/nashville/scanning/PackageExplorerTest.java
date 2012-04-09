package com.tomkp.nashville.scanning;

import com.tomkp.nashville.annotations.Fixture;
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
        assertEquals("TestFixture3", classes.get(1).getSimpleName());
        assertEquals("TestClass1", classes.get(2).getSimpleName());
        assertEquals("TestClass2", classes.get(3).getSimpleName());
        assertEquals("TestFixture1", classes.get(4).getSimpleName());
        assertEquals("TestFixture2", classes.get(5).getSimpleName());
    }

    @Test
    public void findsClassesInPackageWithFixtureFilter() {
        List<Class> classes = new PackageExplorer().getClasses("com.tomkp.testdata.classes", new FixtureFilter());
        assertEquals("TestFixture3", classes.get(0).getSimpleName());
        assertEquals("TestFixture1", classes.get(1).getSimpleName());
        assertEquals("TestFixture2", classes.get(2).getSimpleName());
    }


    @Test
    public void findsClassesInNonExistantPackage() {
        List<Class> classes = new PackageExplorer().getClasses("com.tomkp.testdata.nonexistant");
        assertTrue(classes.isEmpty());
    }

}
