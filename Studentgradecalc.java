package Codsoft;

import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        // Create a Scanner object for taking user input
        Scanner scanner = new Scanner(System.in);
        
        // Number of subjects
        int numSubjects = 5;  // You can change this value if needed
        
        // Array to store marks for each subject
        double[] marks = new double[numSubjects];
        
        // Input marks for each subject
        System.out.println("Enter the marks obtained in " + numSubjects + " subjects (out of 100):");
        
        double totalMarks = 0;
        
        // Take input for marks in each subject
        for (int i = 0; i < numSubjects; i++) {
            System.out.print("Enter marks for subject " + (i + 1) + ": ");
            marks[i] = scanner.nextDouble();
            totalMarks += marks[i];
        }
        
        // Calculate average percentage
        double averagePercentage = (totalMarks / (numSubjects * 100)) * 100;
        
        // Determine grade based on average percentage
        char grade;
        if (averagePercentage >= 90) {
            grade = 'A';
        } else if (averagePercentage >= 75) {
            grade = 'B';
        } else if (averagePercentage >= 60) {
            grade = 'C';
        } else if (averagePercentage >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        
        // Display results
        System.out.println("\nTotal Marks: " + totalMarks + "/" + (numSubjects * 100));
        System.out.println("Average Percentage: " + averagePercentage + "%");
        System.out.println("Grade: " + grade);
        
        // Close the scanner object
        scanner.close();
    }
}
