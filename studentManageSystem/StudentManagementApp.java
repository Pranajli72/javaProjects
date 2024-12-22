import java.io.*;
import java.util.*;

class Student {
    private String name;
    private int rollNumber;
    private String grade;

    public Student(String name, int rollNumber, String grade) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.grade = grade;
    }

    public String getName() {
        return name;
    }

    public int getRollNumber() {
        return rollNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }


    @Override
    public String toString() {
        return "Roll Number: " + rollNumber + ", Name: " + name + ", Grade: " + grade;
    }


    public String toFileString() {
        return rollNumber + "," + name + "," + grade;
    }


    public static Student fromFileString(String data) {
        String[] details = data.split(",");
        return new Student(details[1], Integer.parseInt(details[0]), details[2]);
    }
}

class StudentManagementSystem {
    private List<Student> students;
    private final String FILE_NAME = "students.txt";


    public StudentManagementSystem() {
        students = new ArrayList<>();
        loadFromFile();
    }


    public void addStudent(Student student) {
        students.add(student);
        saveToFile();
        System.out.println("Student added successfully.");
    }


    public void removeStudent(int rollNumber) {
        boolean found = students.removeIf(student -> student.getRollNumber() == rollNumber);
        if (found) {
            saveToFile();
            System.out.println("Student removed successfully.");
        } else {
            System.out.println("Student with Roll Number " + rollNumber + " not found.");
        }
    }

    public void searchStudent(int rollNumber) {
        for (Student student : students) {
            if (student.getRollNumber() == rollNumber) {
                System.out.println("Student Found: " + student);
                return;
            }
        }
        System.out.println("Student with Roll Number " + rollNumber + " not found.");
    }


    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student student : students) {
            System.out.println(student);
        }
    }


    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Student student : students) {
                writer.write(student.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving data to file.");
        }
    }


    private void loadFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("No existing data found. Starting fresh.");
        }
    }
}

public class StudentManagementApp {
    private static Scanner scanner = new Scanner(System.in);
    private static StudentManagementSystem sms = new StudentManagementSystem();

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getUserChoice();
            handleUserChoice(choice);
        }
    }

    private static void displayMenu() {
        System.out.println("\n===============================");
        System.out.println("   Student Management System   ");
        System.out.println("===============================");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Search Student");
        System.out.println("4. Display All Students");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }


    private static int getUserChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a number.");
        }
        return choice;
    }


    private static void handleUserChoice(int choice) {
        switch (choice) {
            case 1:
                addStudentFlow();
                break;
            case 2:
                removeStudentFlow();
                break;
            case 3:
                searchStudentFlow();
                break;
            case 4:
                sms.displayAllStudents();
                break;
            case 5:
                System.out.println("Exiting... Goodbye!");
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice! Please try again.");
        }
    }

    private static void addStudentFlow() {
        System.out.print("Enter student roll number: ");
        int rollNumber = validateIntegerInput();

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter student grade: ");
        String grade = scanner.nextLine();

        if (name.isEmpty() || grade.isEmpty()) {
            System.out.println("Name and grade cannot be empty.");
            return;
        }

        sms.addStudent(new Student(name, rollNumber, grade));
    }

    private static void removeStudentFlow() {
        System.out.print("Enter roll number of student to remove: ");
        int rollNumber = validateIntegerInput();
        sms.removeStudent(rollNumber);
    }

    // Search for a student
    private static void searchStudentFlow() {
        System.out.print("Enter roll number of student to search: ");
        int rollNumber = validateIntegerInput();
        sms.searchStudent(rollNumber);
    }

    // Validate integer input
    private static int validateIntegerInput() {
        int input = -1;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                if (input < 0) {
                    throw new NumberFormatException();
                }
                break;
            } catch (NumberFormatException e) {
                System.out.print("Invalid input! Please enter a positive integer: ");
            }
        }
        return input;
    }
}
