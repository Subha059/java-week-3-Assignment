
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WordCount {
    public static void main(String[] args) {
        File file = new File("sample.txt");
        int count = 0;

        try (Scanner sc = new Scanner(file)) {
            while (sc.hasNext()) {
                sc.next();
                count++;
            }

            System.out.println("Total words = " + count);

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found.");
        }
    }
}
