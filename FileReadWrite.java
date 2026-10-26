
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class FileReadWrite {
    public static void main(String[] args) {
        String fileName = "sample.txt";

        // Write to file
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Hello Java!\n");
            writer.write("File handling example.");
            System.out.println("File written successfully.");

        } catch (IOException e) {
            System.out.println("Error while writing file.");
        }

        // Read from file
        try (FileReader reader = new FileReader(fileName)) {
            int ch;

            System.out.println("File content:");

            while ((ch = reader.read()) != -1) {
                System.out.print((char) ch);
            }

        } catch (IOException e) {
            System.out.println("Error while reading file.");
        }
    }
}

