package com.CsvWriter.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

public class IdWriter implements Writer{

    private final File csvFile;
    private final String delimiter;
    private final String newHeaderName;
    private final int newHeaderPosition;
    private final String newFileLocation;

    public IdWriter(String delimiter, String newHeaderName, int newHeaderPosition, String oldFileLocation, String newFileLocation) {
        this.csvFile = new File(oldFileLocation).getAbsoluteFile();

        this.newFileLocation = newFileLocation;
        this.newHeaderName = newHeaderName;
        this.newHeaderPosition = newHeaderPosition - 1;
        this.delimiter = delimiter;
    }

    @Override
    public void write() {
        Map<String, String[]> data = getOriginalFileData(csvFile);
        String[] origHeaders = data.get("HEADERS");
        String[] allRows = data.get("ROWS");
        int totalNumberOfColumns = origHeaders.length + 1;

        try(FileWriter writer = new FileWriter(this.newFileLocation)) {
            writeHeaders(writer, origHeaders, this.newHeaderName, this.newHeaderPosition, totalNumberOfColumns);
            writeRows(writer, allRows, this.newHeaderPosition, totalNumberOfColumns);
        } catch (IOException e) {
            System.out.println("Error writing to new file");
            e.printStackTrace();
        }
        System.out.println("New CSV file written to: " + this.newFileLocation);

    }

    private void writeHeaders(FileWriter writer, String[] origHeaders,String newHeader, int positionToAdd, int totalNumOfColumns) throws IOException {
        String[] newHeaders = new String[totalNumOfColumns];
        newHeaders[positionToAdd] = newHeader;
        int pos = 0;

        for(int i = 0; i < origHeaders.length; i++) {
            if(positionToAdd == i) {
                pos = positionToAdd + 1;
            }
            newHeaders[pos] = origHeaders[i];
            pos++;
        }

        for(int i = 0; i < newHeaders.length ; i++) {
            if(i < (newHeaders.length - 1)) {
                writer.append(newHeaders[i]).append(this.delimiter);
            } else {
                writer.append(newHeaders[i]);
            }
        }
        writer.append("\n");
    }

    private void writeRows(FileWriter writer, String[] originalRows, int positionToAdd, int totalNumOfColumns) throws IOException {

        String[] newRows = new String[originalRows.length];
        String[] newRowData = new String[totalNumOfColumns];
        for(int i = 0 ; i < originalRows.length ; i++) {
            int pos = 0;
            newRowData[positionToAdd] = String.valueOf(UUID.randomUUID());
            String[] origColumnRowData = originalRows[i].split(getFormattedDelimiter(this.delimiter));
            StringBuilder sb = new StringBuilder();

            for(int r = 0 ; r < origColumnRowData.length; r++) {
                if(positionToAdd == r) {
                    pos = positionToAdd + 1;
                }
                newRowData[pos] = origColumnRowData[r];
                pos++;
            }

            for(int d = 0; d < newRowData.length; d++) {
                if(d < totalNumOfColumns -1) {
                    sb.append(newRowData[d]).append(this.delimiter);
                } else {
                    sb.append(newRowData[d]);
                }
                newRows[i] = String.valueOf(sb);
            }
        }

            for(int i = 0 ; i < newRows.length ; i++) {
                if(i < (newRows.length -1)) {
                    writer.append(newRows[i]).append("\n");
                } else {
                    writer.append(newRows[i]);
                }
            }

    }

    private Map<String, String[]> getOriginalFileData(File csvFile) {
        HashMap<String, String[]> dataMap = new HashMap<>();
        String[] csv;
        try (InputStream inputFS = new FileInputStream(csvFile);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))
        ) {
            if(br.ready()) {
                csv = br.lines().toArray(String[]::new);
                String[] rows = new String[csv.length-1];
                String[] headers = csv[0].split(getFormattedDelimiter(this.delimiter));
                for(int i = 0; i < csv.length; i++) {
                    if(i > 0) {
                        int insertPosition = i - 1;
                        rows[insertPosition] = csv[i];
                    }
                }
                dataMap.put("HEADERS", headers);
                dataMap.put("ROWS", rows);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
            e.printStackTrace();
        }
          return dataMap;
    }

    private String getFormattedDelimiter(String delimiter) {
        return isAlphaNumeric(delimiter) ? delimiter : "\\" + delimiter;
    }

    private boolean isAlphaNumeric(String delimiter) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        return p.matcher(delimiter).find();
    }

}
