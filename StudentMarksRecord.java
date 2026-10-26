
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class StudentMarksRecord {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Integer> marks = new ArrayList<>();

        System.out.print("Enter number of students: ");
        int n = sc.nextInt();

        if (n <= 0) {
            System.out.println("Invalid number of students.");
            sc.close();
            return;
        }

        for (int i = 1; i <= n; i++) {
            System.out.print("Enter marks of student " + i + ": ");
            int mark = sc.nextInt();

            if (mark < 0 || mark > 100) {
                System.out.println("Invalid marks! Enter 0 to 100.");
                i--;
            } else {
                marks.add(mark);
            }
        }

        int highest = Collections.max(marks);
        int lowest = Collections.min(marks);

        System.out.println("All Marks: " + marks);
        System.out.println("Highest Marks: " + highest);
        System.out.println("Lowest Marks: " + lowest);

        sc.close();
    }
}
