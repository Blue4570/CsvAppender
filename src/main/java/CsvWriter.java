import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvWriter {

    private File csvFile;
    private String delimiter;
    static final String NEW_FILE_LOCATION_AND_NAME = "/Users/Aaron/IdeaProjects/CsvAppender/src/main/resources/csv/newCsv.txt";


    public CsvWriter(String delimiter) {
        this.csvFile = new File(getClass().getClassLoader().getResource("csv/TestCsv.txt").getFile());
        this.delimiter = delimiter;
    }

    public void writeFile() {
        List<NewTable> tableList = readFile(csvFile);
        try(FileWriter writer = new FileWriter(NEW_FILE_LOCATION_AND_NAME)) {
            writer.append("ID$FirstName$LastName").append("\n");
            tableList.forEach(table -> {
                try {
                    writer.append(table.getId()).append(delimiter);
                    writer.append(table.getFirstName()).append(delimiter);
                    writer.append(table.getLastName());
                    writer.append("\n");
                } catch (IOException e) {
                    System.out.println("Unable to write new File");
                    e.printStackTrace();
                }
            });
        } catch(IOException e) {
            System.out.println("Unable to write new file");
        }
    }

    private List<NewTable> readFile(File csvFile) {
        List<NewTable> tableList = new ArrayList<>();
        try (InputStream inputFS = new FileInputStream(csvFile);
             BufferedReader br = new BufferedReader(new InputStreamReader(inputFS))
        ) {
            // skip the header of the csv
            tableList = br.lines().skip(1).map(mapToItem).collect(Collectors.toList());

        } catch (IOException e) {
            System.out.println("Error reading file");
        }
        return tableList;
    }

    private Function<String, NewTable> mapToItem = (line) -> {
        Optional<NewTable> newTable = Optional.empty();
        if (line != null) {
            String[] p = line.split("\\$");
             newTable = Optional.of(new NewTable(p[0], p[1]));
        }
        return newTable.orElseGet(NewTable::new);
    };
}
