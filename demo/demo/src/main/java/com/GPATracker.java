package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GPATracker {
    private static Scanner scanner = new Scanner(System.in);
    private static HashMap<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nWelcome to GPA Tracker!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    register();
                    break;
                case 2:
                    User currentUser = login();
                    if (currentUser != null) {
                        displayDashboard(currentUser);
                    }
                    break;
                case 3:
                    System.out.println("\nExiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid choice! Please enter a valid option.");
            }
        }
    }

    private static void register() {
        System.out.println("\nRegistration");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        if (users.containsKey(email)) {
            System.out.println("User already exists. Please choose a different email.");
            return;
        }
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User newUser = new User(email, password);
        users.put(email, newUser);
        System.out.println("Registration successful!");
    }

    private static User login() {
        System.out.println("\nLogin");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        if (!users.containsKey(email) || !users.get(email).getPassword().equals(password)) {
            System.out.println("Invalid email or password! Please try again.");
            return null;
        }
        return users.get(email);
    }

    private static void displayDashboard(User user) {
        while (true) {
            System.out.println("\nDashboard");
            System.out.println("1. Add Course");
            System.out.println("2. View Courses");
            System.out.println("3. Calculate GPA");
            System.out.println("4. Logout");
            System.out.println("---------------------");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addCourse(user);
                    break;
                case 2:
                    viewCourses(user);
                    break;
                case 3:
                    calculateGPA(user);
                    break;
                case 4:
                    System.out.println("\nLogging out...");
                    return;
                default:
                    System.out.println("\nInvalid choice! Please enter a valid option.");
            }
        }
    }

    private static void addCourse(User user) {
        System.out.println("\nAdd Course");
        System.out.print("Enter course name: ");
        String name = scanner.nextLine();
        System.out.print("Enter credit hours: ");
        int creditHours = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter grade (e.g., A, B+, C-, etc.): ");
        String grade = scanner.nextLine().toUpperCase();
        user.addCourse(name, creditHours, grade);
        System.out.println("Course added successfully!\n");
    }

    private static void viewCourses(User user) {
        ArrayList<Course> courses = user.getCourses();
        if (courses.isEmpty()) {
            System.out.println("\nNo courses added yet.\n");
        } else {
            System.out.println("\nCourses:");
            for (Course course : courses) {
                System.out.println("Name: " + course.getName() + ", Credit Hours: " + course.getCreditHours() + ", Grade: " + course.getGrade());
            }
            System.out.println();
        }
    }

    private static void calculateGPA(User user) {
        ArrayList<Course> courses = user.getCourses();
        if (courses.isEmpty()) {
            System.out.println("\nNo courses added yet. GPA calculation is not possible.\n");
            return;
        }

        int totalCreditHours = 0;
        double totalGradePoints = 0.0;
        for (Course course : courses) {
            totalCreditHours += course.getCreditHours();
            totalGradePoints += getGradePoints(course.getGrade()) * course.getCreditHours();
        }
        
        double gpa = totalGradePoints / totalCreditHours;
        System.out.println("\nGPA: " + String.format("%.2f", gpa) + "\n");
    }

    private static double getGradePoints(String grade) {
        switch (grade) {
            case "A+":
            case "A":
                return 4.0;
            case "A-":
                return 3.7;
            case "B+":
                return 3.3;
            case "B":
                return 3.0;
            case "B-":
                return 2.7;
            case "C+":
                return 2.3;
                case "C":
                return 2.0;
            case "C-":
                return 1.7;
            case "D+":
                return 1.3;
            case "D":
                return 1.0;
            case "D-":
                return 0.7;
            case "F":
                return 0.0;
            default:
                return 0.0;
        }
    }
}