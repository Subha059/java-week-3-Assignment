
import java.io.*;
import java.util.*;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String toString() {
        return "ID: " + id +
               ", Name: " + name +
               ", Department: " + department +
               ", Salary: " + salary;
    }

    String toFile() {
        return id + "," + name + "," + department + "," + salary;
    }
}

public class EmployeeManagementSystem {
    static ArrayList<Employee> employees = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    static final String FILE_NAME = "employees.txt";

    static void addEmployee() {
        try {
            System.out.print("Enter ID: ");
            int id = Integer.parseInt(sc.nextLine().trim());

            for (Employee e : employees) {
                if (e.id == id) {
                    System.out.println("Error: ID already exists.");
                    return;
                }
            }

            System.out.print("Enter Name: ");
            String name = sc.nextLine().trim();

            System.out.print("Enter Department: ");
            String department = sc.nextLine().trim();

            System.out.print("Enter Salary: ");
            double salary = Double.parseDouble(sc.nextLine().trim());

            if (id <= 0 || name.isEmpty() ||
                department.isEmpty() ||
                !Double.isFinite(salary) || salary < 0) {
                System.out.println("Error: Invalid employee details.");
                return;
            }

            if (name.contains(",") || department.contains(",")) {
                System.out.println("Name and department cannot contain commas.");
                return;
            }

            employees.add(new Employee(id, name, department, salary));
            System.out.println("Employee added successfully.");

        } catch (NumberFormatException e) {
            System.out.println("Error: Enter a valid ID and salary.");
        }
    }

    static void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employee records found.");
            return;
        }

        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    static void saveEmployees() {
        try (PrintWriter writer =
                 new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Employee e : employees) {
                writer.println(e.toFile());
            }

            System.out.println("Employee data saved to " + FILE_NAME);

        } catch (IOException e) {
            System.out.println("Error: Could not save employee data.");
        }
    }

    static void loadEmployees() {
        ArrayList<Employee> loaded = new ArrayList<>();
        HashSet<Integer> ids = new HashSet<>();

        try (BufferedReader reader =
                 new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {
                try {
                    String[] data = line.split(",", -1);

                    if (data.length != 4) {
                        throw new IllegalArgumentException();
                    }

                    int id = Integer.parseInt(data[0].trim());
                    String name = data[1].trim();
                    String department = data[2].trim();
                    double salary = Double.parseDouble(data[3].trim());

                    if (id <= 0 || name.isEmpty() ||
                        department.isEmpty() ||
                        !Double.isFinite(salary) || salary < 0 ||
                        !ids.add(id)) {
                        throw new IllegalArgumentException();
                    }

                    loaded.add(new Employee(id, name, department, salary));

                } catch (IllegalArgumentException e) {
                    System.out.println("Skipping invalid record: " + line);
                }
            }

            employees.clear();
            employees.addAll(loaded);
            System.out.println("Employee data loaded successfully.");

        } catch (FileNotFoundException e) {
            System.out.println("No saved file found. Add employees first.");

        } catch (IOException e) {
            System.out.println("Error: Could not read employee data.");
        }
    }

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n--- Employee Management System ---");
            System.out.println("1. Add Employee");
            System.out.println("2. Display Employees");
            System.out.println("3. Save Employees");
            System.out.println("4. Load Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            String choice = sc.nextLine().trim();

            switch (choice) {
                case "1":
                    addEmployee();
                    break;

                case "2":
                    displayEmployees();
                    break;

                case "3":
                    saveEmployees();
                    break;

                case "4":
                    loadEmployees();
                    break;

                case "5":
                    System.out.println("Program ended.");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Enter 1 to 5.");
            }
        }
    }
}
