package com.CsvWriter.model;

public class UserInput {

    private String origFileLocation;
    private String newFileLocation;
    private int newHeaderPosition;
    private String delimiter;
    private String newHeaderName;

    public UserInput() {}

    public UserInput(String origFileLocation, String newFileLocation, int newHeaderPosition, String delimiter, String newHeaderName) {
        this.origFileLocation = origFileLocation;
        this.newFileLocation = newFileLocation;
        this.newHeaderPosition = newHeaderPosition;
        this.delimiter = delimiter;
        this.newHeaderName = newHeaderName;
    }

    public String getOrigFileLocation() {
        return origFileLocation;
    }

    public void setOrigFileLocation(String origFileLocation) {
        this.origFileLocation = origFileLocation;
    }

    public String getNewFileLocation() {
        return newFileLocation;
    }

    public void setNewFileLocation(String newFileLocation) {
        this.newFileLocation = newFileLocation;
    }

    public int getNewHeaderPosition() {
        return newHeaderPosition;
    }

    public void setNewHeaderPosition(int newHeaderPosition) {
        this.newHeaderPosition = newHeaderPosition;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getNewHeaderName() {
        return newHeaderName;
    }

    public void setNewHeaderName(String newHeaderName) {
        this.newHeaderName = newHeaderName;
    }
}
