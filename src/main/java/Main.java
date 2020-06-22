import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Writer writer = new Writer("$", "ID", 1);
        try {
            writer.write();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
