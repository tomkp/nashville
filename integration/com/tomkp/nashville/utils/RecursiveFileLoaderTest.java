package com.tomkp.nashville.utils;

import com.tomkp.nashville.utils.RecursiveFileLoader;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RecursiveFileLoaderTest {


    private final RecursiveFileLoader fileLoader = new RecursiveFileLoader("feature");


    @Test
    public void loadFeatures() {
        List<File> features = fileLoader.findFeatures(new File("testdata/features"));
        assertEquals(features.get(0).getName(), "test-feature3.feature");
        assertEquals(features.get(1).getName(), "test-feature1.feature");
        assertEquals(features.get(2).getName(), "test-feature2.feature");
        assertEquals(features.size(), 3);
    }

    @Test
    public void loadFeaturesFromNonExistentDirectory() {
        List<File> features = fileLoader.findFeatures(new File("testdata/nonexistent"));
        assertTrue(features.isEmpty());
    }
}
