package com.ei.classroom;

import com.ei.classroom.manager.ClassroomManager;
import com.ei.classroom.patterns.behavioral.ObserverDemo;
import com.ei.classroom.patterns.behavioral.StrategyDemo;
import com.ei.classroom.patterns.creational.SingletonDemo;
import com.ei.classroom.patterns.creational.FactoryDemo;
import com.ei.classroom.patterns.structural.DecoratorDemo;
import com.ei.classroom.patterns.structural.AdapterDemo;
import com.ei.classroom.util.AppLogger;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class MainApplication {
    private static final String CLASS_NAME = "MainApplication";
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final ClassroomManager MANAGER = ClassroomManager.getInstance();

    public static void main(String[] args) {
        System.out.println("*****************************");
        System.out.println("* Virtual Classroom Manager *");
        System.out.println("*****************************");
        
        showInitialMenu();
    }
    
    private static void showInitialMenu() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- Select Exercise ---");
            System.out.println("1. Run EXERCISE 1: Design Pattern Demonstrations (6 Demos)");
            System.out.println("2. Start EXERCISE 2: Virtual Classroom Manager Application");
            System.out.println("0. Exit Application");
            System.out.print("Select an option (0-2): ");
            
            try {
                if (SCANNER.hasNextInt()) {
                    int choice = SCANNER.nextInt();
                    SCANNER.nextLine(); // Consume newline
                    
                    switch (choice) {
                        case 1: 
                            runPatternDemos();
                            break;
                        case 2: 
                            startClassroomManager();
                           
                            break; 
                        case 0:
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid choice. Please select 0, 1, or 2.");
                    }
                } else {
                    SCANNER.nextLine(); 
                    System.out.println("Invalid input. Please enter a number.");
                }
            } catch (Exception e) {
                AppLogger.logError(CLASS_NAME, "An unexpected error occurred during initial menu selection.", e);
                running = false;
            }
        }
        System.out.println("\nVirtual Classroom Manager shutting down. Goodbye!");
        SCANNER.close();
    }


    private static void runPatternDemos() {
        System.out.println("\n--- EXERCISE 1: DESIGN PATTERN DEMONSTRATIONS ---");
        
        ObserverDemo.runDemo();
        StrategyDemo.runDemo();
        SingletonDemo.runDemo();
        FactoryDemo.runDemo();
        DecoratorDemo.runDemo();
        AdapterDemo.runDemo();
        
        System.out.println("--- EXERCISE 1 DEMOS COMPLETE. Returning to main selection. ---\n");
    }

    private static void startClassroomManager() {
        System.out.println("\n--- EXERCISE 2: VIRTUAL CLASSROOM MANAGER ---");
        
        boolean running = true;
        while (running) {
            displayMenu();
            try {
                if (SCANNER.hasNextInt()) {
                    int choice = SCANNER.nextInt();
                    SCANNER.nextLine(); 
                    if (choice == 0) {
                        running = false; 
                        System.out.println("Exiting application menu. Returning to initial selection.");
                        continue; 
                    }
                    handleChoice(choice);
                } else {
                    String invalidInput = SCANNER.nextLine();
                    AppLogger.logError(CLASS_NAME, "Invalid menu input: " + invalidInput);
                    System.out.println("Invalid input. Please enter a number from the menu.");
                }
            } catch (InputMismatchException e) {
                AppLogger.logError(CLASS_NAME, "Invalid input type provided.", e);
                System.out.println("Invalid input type. Please enter a valid number.");
                SCANNER.nextLine();
            } catch (NoSuchElementException e) {
                AppLogger.logError(CLASS_NAME, "Input stream error or unexpected end of input.", e);
                System.out.println("An unexpected input error occurred. Restarting menu...");
            }
             catch (Exception e) {
                // General exception handling
                AppLogger.logError(CLASS_NAME, "An application error occurred.", e);
                System.out.println("ERROR: " + e.getMessage());
            }
        }
    }

    // --- Helper methods (displayMenu, handleChoice, addClassroom, etc.) remain the same ---

    private static void displayMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. Add Classroom");
        System.out.println("2. Enroll Student");
        System.out.println("3. Schedule Assignment");
        System.out.println("4. Submit Assignment");
        System.out.println("5. List Classrooms");
        System.out.println("6. List Students in Class");
        System.out.println("0. Exit Application Menu (Go back)");
        System.out.print("Select an option (0-6): ");
    }
    
    private static void handleChoice(int choice) throws Exception {
        switch (choice) {
            case 1: addClassroom(); break;
            case 2: enrollStudent(); break;
            case 3: scheduleAssignment(); break;
            case 4: submitAssignment(); break;
            case 5: MANAGER.listClassrooms(); break;
            case 6: listStudentsInClass(); break;
            // Case 0 is handled in startClassroomManager
            default: System.out.println("Choice " + choice + " is not valid. Please try again.");
        }
    }
    
    private static void addClassroom() {
        System.out.print("Enter new classroom name: ");
        String name = SCANNER.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Classroom name cannot be empty.");
            return;
        }
        MANAGER.addClassroom(name);
    }
    
    private static void enrollStudent() {
        System.out.print("Enter student ID (e.g., S001): ");
        String id = SCANNER.nextLine().trim();
        System.out.print("Enter student Name: ");
        String name = SCANNER.nextLine().trim();
        System.out.print("Enter classroom name to enroll in: ");
        String className = SCANNER.nextLine().trim();
        
        if (id.isEmpty() || name.isEmpty() || className.isEmpty()) {
            System.out.println("All fields are required for enrollment.");
            return;
        }
        
        try {
            MANAGER.enrollStudent(id, name, className);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void scheduleAssignment() {
        System.out.print("Enter classroom name: ");
        String className = SCANNER.nextLine().trim();
        System.out.print("Enter assignment name: ");
        String assignmentName = SCANNER.nextLine().trim();
        System.out.print("Enter assignment type (MCQ, ESSAY, PRACTICAL): ");
        String type = SCANNER.nextLine().trim();

        if (className.isEmpty() || assignmentName.isEmpty() || type.isEmpty()) {
            System.out.println("All assignment details are required.");
            return;
        }
        
        try {
            MANAGER.scheduleAssignment(className, assignmentName, type);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void submitAssignment() {
        System.out.print("Enter student ID submitting: ");
        String studentId = SCANNER.nextLine().trim();
        System.out.print("Enter classroom name: ");
        String className = SCANNER.nextLine().trim();
        System.out.print("Enter assignment name submitting: ");
        String assignmentName = SCANNER.nextLine().trim();

        if (studentId.isEmpty() || className.isEmpty() || assignmentName.isEmpty()) {
            System.out.println("All submission fields are required.");
            return;
        }

        try {
            MANAGER.submitAssignment(studentId, className, assignmentName); 
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static void listStudentsInClass() {
        System.out.print("Enter classroom name to view students: ");
        String className = SCANNER.nextLine().trim();
        
        if (className.isEmpty()) {
            System.out.println("Classroom name is required.");
            return;
        }

        try {
            MANAGER.listStudentsInClass(className);
        } catch (NoSuchElementException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

