package com.tomkp.nashville.coercion;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileCoercer implements Coercer<File> {

    private static final Logger LOG = LoggerFactory.getLogger(FileCoercer.class);

    @Override
    public File coerce(String value, String format) {
        LOG.info("coerce '{}'", value);
        File file = new File(value);
        LOG.info("file '{}' {}", file.getAbsolutePath(), file.exists()?"exists":"does not exist");
        return file;
    }

}
