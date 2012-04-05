package com.tomkp.nashville.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.List;

public class RecursiveFileLoader {

    private static final Logger LOG = LoggerFactory.getLogger(RecursiveFileLoader.class);

    private final String suffix;

    public RecursiveFileLoader(final String suffix) {
        this.suffix = suffix;
    }


    public List<File> findFeatures(File dir) {
        LOG.info("find features in directory: '{}'", dir.getAbsolutePath());
        List<File> files = new ArrayList<File>();
        recurse(dir, files);
        return files;
    }


    private void recurse(File dir, List<File> list) {
        LOG.info("search for specs in dir: '{}'", dir.getAbsolutePath());
        if (dir.exists()) {
            if (dir.isDirectory()) {
                File[] files = listFiles(dir);
                for (File file : files) {
                    if (file.isDirectory()) {
                        recurse(file, list);
                    } else {
                        list.add(file);
                    }
                }
            }
        } else {
            LOG.warn("'{}' does not exist", dir);
        }
    }


    private File[] listFiles(File dir) {
        return dir.listFiles(new FileFilter() {
            @Override
            public boolean accept(File file) {
                String name = file.getName();
                return matched(file, name);
            }
        });
    }

    private boolean matched(File file, String name) {
        return (name.endsWith(suffix) || file.isDirectory())
                && !isSubversionDirectory(name)
                && !name.startsWith("before")
                && !name.startsWith("after");
    }

    private boolean isSubversionDirectory(String name) {
        return ".svn".equals(name);
    }


}
