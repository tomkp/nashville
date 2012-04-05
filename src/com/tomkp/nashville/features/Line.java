package com.tomkp.nashville.features;

import java.io.File;

public class Line {

    private int number;
    private String contents;
    private File file;

    public Line(File file, int number, String contents) {
        this.file = file;
        this.number = number;
        this.contents = contents;
    }

    public File getFile() {
        return file;
    }

    public int getNumber() {
        return number;
    }

    public String getContents() {
        return contents;
    }

    public boolean isEmpty() {
        return contents.isEmpty();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("").append(file.getAbsoluteFile()).append(":").append(number).append("): ");
        sb.append("\"").append(contents).append("\"");
        return sb.toString();
    }
}
