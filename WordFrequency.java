
import java.io.*;
import java.util.*;

public class WordFrequency {
    public static void main(String[] args) {
        Map<String, Integer> frequency = new LinkedHashMap<>();

        try (Scanner sc = new Scanner(new File("input.txt"))) {
            while (sc.hasNext()) {
                String word = sc.next()
                        .toLowerCase()
                        .replaceAll("^[^a-z0-9]+|[^a-z0-9]+$", "");

                if (!word.isEmpty()) {
                    frequency.put(
                        word,
                        frequency.getOrDefault(word, 0) + 1
                    );
                }
            }

            try (PrintWriter writer =
                     new PrintWriter(new FileWriter("output.txt"))) {

                for (Map.Entry<String, Integer> entry
                        : frequency.entrySet()) {
                    writer.println(
                        entry.getKey() + " : " + entry.getValue()
                    );
                }
            }

            System.out.println("Word frequency saved to output.txt");

        } catch (FileNotFoundException e) {
            System.out.println("Error: input.txt not found.");

        } catch (IOException e) {
            System.out.println("Error while writing output file.");
        }
    }
}
