import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Writer {

    private final File csvFile;
    private final String delimiter;
    private final String formattedDelimiter;
    private final String newHeader;
    private final int newHeaderPosition;
    static final String NEW_FILE_LOCATION_AND_NAME = "/Users/Aaron/IdeaProjects/CsvAppender/src/main/resources/csv/newCsv.txt";

    public Writer(String delimiter, String newheader, int newHeaderPosition) {
        this.csvFile = new File(getClass().getClassLoader().getResource("csv/TestCsv.txt").getFile());
        this.newHeader = newheader;
        this.newHeaderPosition = newHeaderPosition;
        this.delimiter = delimiter;
        this.formattedDelimiter = getDelimiter(delimiter);
    }

    public void write() throws IOException {
       // FileWriter writer = getFileWriter();
        Map<String, String[]> data = getOriginalFileData(csvFile);
        String[] origHeaders = data.get("HEADERS");
      //  String[] allRows = data.get("ROWS");
        int totalNumberOfColumns = origHeaders.length + 1;
        try(FileWriter writer = new FileWriter(NEW_FILE_LOCATION_AND_NAME)) {
            writeHeaders(writer, origHeaders, this.newHeader, this.newHeaderPosition, totalNumberOfColumns);
        } catch (IOException e) {
            System.out.println("Error writing to new file");
            e.printStackTrace();
        }
        System.out.println("New CSV file written to: " + NEW_FILE_LOCATION_AND_NAME);

    }

    private void writeHeaders(FileWriter writer, String[] origHeaders,String newHeader, int newHeaderPosition, int totalNumOfColumns) throws IOException {
        String[] newHeaders = new String[totalNumOfColumns];
        int posToAdd = newHeaderPosition - 1;
        newHeaders[posToAdd] = newHeader;
        int pos = 0;

        for(int i = 0; i < origHeaders.length; i++) {
            if(posToAdd == i) {
                pos = posToAdd + 1;
            }
            newHeaders[pos] = origHeaders[i];
            pos++;
        }

        for (String s: newHeaders) {
            writer.append(s).append(delimiter);
        }
        writer.append("\n");
    }

    private void writeRows() {

    }

    private Map<String, String[]> getOriginalFileData(File csvFile) {
        HashMap<String, String[]> dataMap = new HashMap<>();
        String[] csv;
        try (InputStream inputFS = new FileInputStream(csvFile);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))
        ) {
            if(br.ready()) {
                csv = br.lines().toArray(String[]::new);
                String header = csv[0];
                String[] headers = header.split(this.formattedDelimiter);
                dataMap.put("HEADERS", headers);
                //     dataMap.put("ROWS", csv);
            } else {
                throw new IOException();
            }
        } catch (IOException e) {
            System.out.println("Error reading file");
        }
          return dataMap;
    }

    //private final Function<String, String[]> getData = (line) -> line.split("\n");

    private FileWriter getFileWriter() throws IOException {
        return new FileWriter(NEW_FILE_LOCATION_AND_NAME);
    }

    private String getDelimiter(String delimiter) {
        return isAlphaNumeric(delimiter) ? delimiter : "\\" + delimiter;
    }

    private boolean isAlphaNumeric(String delimiter) {
        Pattern p = Pattern.compile("[a-zA-Z0-9]");
        return p.matcher(delimiter).find();
    }


}
