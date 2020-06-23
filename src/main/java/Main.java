import builders.WriterBuilder;
import model.UserInput;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UserInput userInput = new UserInput();

        System.out.println("*** - Welcome to CsvWriter - *** \n");

        System.out.println("Enter a delimiter: ");
        userInput.setDelimiter(sc.nextLine());

        System.out.println("What is the new header name: ");
        userInput.setNewHeaderName(sc.nextLine());

        System.out.println("Position where you want the new Id column added: ");
        userInput.setNewHeaderPosition(sc.nextInt());

        System.out.println("Full path to the original Csv file: ");
        userInput.setOrigFileLocation(sc.next());

        System.out.println("Full path to the location where you want to save the new Csv file: ");
        userInput.setNewFileLocation(sc.next());

        new WriterBuilder(userInput)
                .getWriter()
                .write();
    }
}
