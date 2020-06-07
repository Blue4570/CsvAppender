public class Main {
    public static void main(String[] args) {
        CsvAppender csvAppender = new CsvAppender("$");
        csvAppender.writeFile();
    }
}
