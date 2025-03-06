package Codsoft;

import java.util.*;

public class StudentCourseReg {

    // Define the Course class
    static class Course {
        String courseCode;
        String title;
        String description;
        int capacity;
        int registeredStudents;
        String schedule;

        public Course(String courseCode, String title, String description, int capacity, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.registeredStudents = 0;
            this.schedule = schedule;
        }

        // Method to check if a course is full
        public boolean isFull() {
            return registeredStudents >= capacity;
        }

        // Method to register a student for this course
        public boolean registerStudent() {
            if (isFull()) {
                return false; // Can't register if the course is full
            }
            registeredStudents++;
            return true;
        }

        // Method to drop a student from the course
        public boolean dropStudent() {
            if (registeredStudents > 0) {
                registeredStudents--;
                return true;
            }
            return false;
        }

        // Method to display course details
        public void displayCourseDetails() {
            System.out.println("Course Code: " + courseCode);
            System.out.println("Title: " + title);
            System.out.println("Description: " + description);
            System.out.println("Schedule: " + schedule);
            System.out.println("Capacity: " + capacity);
            System.out.println("Registered Students: " + registeredStudents);
            System.out.println("Available Slots: " + (capacity - registeredStudents));
            System.out.println("-----------------------------------------------");
        }
    }

    // Define the Student class
    static class Student {
        String studentID;
        String name;

        List<Course> registeredCourses;

        public Student(String studentID, String name) {
            this.studentID = studentID;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        // Register a student for a course
        public boolean registerForCourse(Course course) {
            if (course.registerStudent()) {
                registeredCourses.add(course);
                return true;
            }
            return false;
        }

        // Drop a course the student is registered for
        public boolean dropCourse(Course course) {
            if (course.dropStudent()) {
                registeredCourses.remove(course);
                return true;
            }
            return false;
        }

        // Display registered courses for the student
        public void displayRegisteredCourses() {
            if (registeredCourses.isEmpty()) {
                System.out.println("No courses registered.");
            } else {
                System.out.println("Registered Courses for " + name + ":");
                for (Course course : registeredCourses) {
                    System.out.println(course.courseCode + " - " + course.title);
                }
            }
            System.out.println("-----------------------------------------------");
        }
    }

    // Main method to interact with the system
    public static void main(String[] args) {
        // Initialize sample course data
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("CS101", "Introduction to Computer Science", "Learn the basics of computer science.", 50, "Mon/Wed 9:00 AM - 11:00 AM"));
        courses.add(new Course("MATH101", "Calculus I", "An introduction to calculus.", 30, "Tue/Thu 1:00 PM - 3:00 PM"));
        courses.add(new Course("ENG101", "English Composition", "Improve writing and communication skills.", 40, "Mon/Fri 10:00 AM - 12:00 PM"));

        // Initialize student database
        List<Student> students = new ArrayList<>();
        students.add(new Student("S101", "Alice"));
        students.add(new Student("S102", "Bob"));
        students.add(new Student("S103", "Charlie"));

        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("===== Welcome to the Student Course Registration System =====");
            System.out.println("1. View Available Courses");
            System.out.println("2. Register for a Course");
            System.out.println("3. Drop a Course");
            System.out.println("4. View Registered Courses");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume the newline character

            switch (choice) {
                case 1: // View Available Courses
                    System.out.println("\nAvailable Courses:");
                    for (Course course : courses) {
                        course.displayCourseDetails();
                    }
                    break;

                case 2: // Register for a Course
                    System.out.print("\nEnter your student ID: ");
                    String studentID = scanner.nextLine();
                    Student student = findStudentByID(students, studentID);
                    if (student != null) {
                        System.out.println("\nEnter the course code to register:");
                        String courseCode = scanner.nextLine();
                        Course course = findCourseByCode(courses, courseCode);
                        if (course != null) {
                            if (student.registerForCourse(course)) {
                                System.out.println("Successfully registered for " + course.title);
                            } else {
                                System.out.println("Sorry, the course is full or already registered.");
                            }
                        } else {
                            System.out.println("Invalid course code.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 3: // Drop a Course
                    System.out.print("\nEnter your student ID: ");
                    studentID = scanner.nextLine();
                    student = findStudentByID(students, studentID);
                    if (student != null) {
                        System.out.println("\nEnter the course code to drop:");
                        String courseCode = scanner.nextLine();
                        Course course = findCourseByCode(courses, courseCode);
                        if (course != null) {
                            if (student.dropCourse(course)) {
                                System.out.println("Successfully dropped the course " + course.title);
                            } else {
                                System.out.println("You are not registered for this course.");
                            }
                        } else {
                            System.out.println("Invalid course code.");
                        }
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 4: // View Registered Courses
                    System.out.print("\nEnter your student ID: ");
                    studentID = scanner.nextLine();
                    student = findStudentByID(students, studentID);
                    if (student != null) {
                        student.displayRegisteredCourses();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;

                case 5: // Exit
                    System.out.println("Exiting the system.");
                    exit = true;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // Method to find a student by ID
    public static Student findStudentByID(List<Student> students, String studentID) {
        for (Student student : students) {
            if (student.studentID.equals(studentID)) {
                return student;
            }
        }
        return null;
    }

    // Method to find a course by course code
    public static Course findCourseByCode(List<Course> courses, String courseCode) {
        for (Course course : courses) {
            if (course.courseCode.equals(courseCode)) {
                return course;
            }
        }
        return null;
    }
}
