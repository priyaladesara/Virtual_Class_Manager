Virtual Classroom Manager - Design Pattern & Mini-Project Solution
This repository contains the solution for the two coding exercises, implemented in Java, adhering to the required design patterns, SOLID principles, and Gold Standards (logging, exception handling).

Project Structure Overview
The code is logically separated into two main areas under the com.ei.classroom package:

Application Core (manager/, model/, factory/, util/): Implements the Exercise 2: Virtual Classroom Manager console application.

Pattern Demos (patterns/): Contains standalone code demonstrations for the six required design patterns (Exercise 1).

Exercise 1: Design Pattern Implementation
The following six patterns cover all three categories and are demonstrated in the patterns/ directory:

Category

Pattern

Use Case

Reused in Ex 2?

Behavioral

Observer

Student Progress Alerts (implemented using Student and Teacher classes)

YES (Submission alerts)

Behavioral

Strategy

Adaptive Quiz Difficulty

NO (Standalone Demo)

Creational

Singleton

Classroom Manager

YES (ClassroomManager.java)

Creational

Factory

Task/Assignment Factory

YES (AssignmentFactory.java)

Structural

Decorator

Assignment Analytics (Adding time/difficulty tags)

NO (Standalone Demo)

Structural

Adapter

Legacy Data Integration (CSV to Student object)

NO (Standalone Demo)

Exercise 2: Virtual Classroom Manager
The application is run via MainApplication.java.

Key Design Decisions
Design Patterns:

Singleton: Ensures only one instance of ClassroomManager exists, managing all global state.

Factory: AssignmentFactory abstracts assignment creation for easy extensibility.

Observer: When a Student submits an assignment, all attached Teacher observers are immediately notified.

Gold Standards:

Logging: Centralized via AppLogger for Info, Error, and Debug messages.

Exception Handling: Robust try-catch blocks are used in MainApplication.java to handle user input errors (InputMismatchException) and logical application errors (NoSuchElementException, IllegalArgumentException).

Defensive Programming: Null checks and validation on input are performed before processing in ClassroomManager methods.

How to Run
Compile all Java files in the src directory.

Run the main class: com.ei.classroom.MainApplication.