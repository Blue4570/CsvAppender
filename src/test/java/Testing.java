import org.junit.Ignore;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Testing {

    static final String NEW_FILE_LOCATION_AND_NAME = "/Users/Aaron/IdeaProjects/CsvAppender/src/test/resources/newCsv.txt";

    @Ignore
    @Test
    public void test() {
        LinkedList<String> list = new LinkedList<>(Arrays.asList("A", "B"));
        list.addFirst("C");
        list.forEach(System.out::println);
    }

    //TODO Refactor to handle any position for the new header
    @Ignore
    @Test
    public void addHeadersTest() {
        String[] origHeaders = {"First", "Last"};
        String addedheader = "Id";
        int newHeaderPosition = 2;
        String[] newHeaders = new String[3];
        int posToAdd = newHeaderPosition - 1;
        newHeaders[posToAdd] = addedheader;
        int pos = 0;

        for(int i = 0; i < origHeaders.length; i++) {
            if(posToAdd == i) {
                pos = posToAdd + 1;
            }
            newHeaders[pos] = origHeaders[i];
            System.out.println(newHeaders[pos]);
            pos++;
        }

        String delimiter = "$";
        StringBuilder buffer = new StringBuilder();
        for (String s: newHeaders) {
           buffer.append(s).append(delimiter);
        }
        buffer.append("\n");
        buffer.append("newLine");

        System.out.println(buffer.toString());
    }

    private void writeHeaders(String newHeader, int newHeaderPosition, int totalNumberOfColumns) {
        String[] origHeaders = {"First", "Last"};
        String[] newHeaders = new String[totalNumberOfColumns];
        int posToAdd = newHeaderPosition - 1;
        newHeaders[posToAdd] = newHeader;
        int pos = 0;

        for(int i = 0; i < origHeaders.length; i++) {
            if(posToAdd == i) {
                pos = posToAdd + 1;
            }
            newHeaders[pos] = origHeaders[i];
            System.out.println(newHeaders[pos]);
            pos++;
        }

        String delimiter = "$";
        StringBuilder buffer = new StringBuilder();
        for (String s: newHeaders) {
            buffer.append(s).append(delimiter);
        }
        buffer.append("\n");
        buffer.append("newLine");

        System.out.println(buffer.toString());
    }


//    @Ignore
//    @Test
//    public void writeFile() {
//        try(FileWriter writer = new FileWriter(NEW_FILE_LOCATION_AND_NAME)) {
//            writer
//        } catch (IOException e) {
//            System.out.println("I/O Error");
//            e.printStackTrace();
//        }
//
//    }

    @Ignore
    @Test
    public void testInt() {
        int i = 1;
        System.out.println(--i);
    }

}
