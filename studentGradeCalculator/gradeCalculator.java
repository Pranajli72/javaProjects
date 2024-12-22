import java.util.Scanner;

public class gradeCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        int[] marks = new int[numberOfSubjects];

        for (int i = 0; i < numberOfSubjects; i++) {
            int mark;
            do {
                System.out.print("Enter marks obtained in subject " + (i + 1) + " (out of 100): ");
                mark = scanner.nextInt();
                if (mark < 0 || mark > 100) {
                    System.out.println("Wrong marks! Please enter marks between 0 and 100.");
                }
            } while (mark < 0 || mark > 100); 
            marks[i] = mark;
        }

        int totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }

        double averagePercentage = (double) totalMarks / numberOfSubjects;

        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 80) {
            grade = 'B';
        } else if (averagePercentage >= 70) {
            grade = 'C';
        } else if (averagePercentage >= 60) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        System.out.println("\n--- Results ---");
        System.out.println("Total Marks: " + totalMarks);
        System.out.printf("Average Percentage: %.2f%%\n", averagePercentage);
        System.out.println("Grade: " + grade);

        scanner.close();
    }
}
