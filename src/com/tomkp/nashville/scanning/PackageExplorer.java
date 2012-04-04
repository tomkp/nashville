package com.tomkp.nashville.scanning;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class PackageExplorer {


    private static final Logger LOG = LoggerFactory.getLogger(PackageExplorer.class);


    public List<Class> getClasses(String packageName) {
        if (LOG.isInfoEnabled()) LOG.info("find classes in package '{}'", packageName);
        List<Class> classes = new ArrayList<Class>();
        try {
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            String path = packageName.replace('.', '/');
            Enumeration<URL> resources = classLoader.getResources(path);
            List<File> dirs = new ArrayList<File>();
            while (resources.hasMoreElements()) {
                URL resource = resources.nextElement();
                dirs.add(new File(resource.getFile()));
            }
            for (File directory : dirs) {
                classes.addAll(findClassesInDirectory(directory, packageName));
            }
        } catch (Exception e) {
            throw new RuntimeException("unable to find classes in '" + packageName + "'", e);
        }
        return classes;
    }


    private List<Class> findClassesInDirectory(File directory, String packageName) throws ClassNotFoundException {
        if (LOG.isDebugEnabled()) LOG.debug("scan directory '{}'", directory);
        List<Class> classes = new ArrayList<Class>();
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    classes.addAll(findClassesInDirectory(file, packageName + "." + file.getName()));
                } else if (file.getName().endsWith(".class")) {
                    String className = className(packageName, file);
                    Class<?> clas = Class.forName(className);
                    classes.add(clas);
                }
            }
        }
        return classes;
    }

    private String className(String packageName, File file) {
        return packageName + '.' + file.getName().substring(0, file.getName().length() - 6);
    }
}
