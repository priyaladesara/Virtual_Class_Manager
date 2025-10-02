# Virtual Classroom Management System

This repository contains the solution for a two-part coding assignment focused on demonstrating mastery of **Software Design Patterns** (Exercise 1) and applying those principles to build a **Console-Based Mini-Project** (Exercise 2).

The project is built using **Java** and follows object-oriented programming (OOP) best practices, SOLID principles, and includes "gold standard" mechanisms for logging and exception handling.

---

##  Getting Started

### Prerequisites
- **Java Development Kit (JDK) 17** or newer installed
- A console/terminal environment (CMD, PowerShell, or equivalent)

### How to Run

The application includes an initial menu allowing the user to choose between the Pattern Demonstrations (Ex 1) or the main Application (Ex 2).

1. **Compile the source files:**
   
   Navigate to the root directory of the project (`VirtualClassroomProject/`) and compile all Java files.
```bash
javac -d bin src/com/ei/classroom/*/*.java src/com/ei/classroom/*.java src/com/ei/classroom/patterns/*/*.java
```
Execute the application:
Run the compiled main class.
```bash
java -cp bin com.ei.classroom.MainApplication
```

## Project Structure
The project maintains a clear separation of concerns between the core application logic (manager, model) and the standalone pattern demonstrations (patterns/).

<img width="1097" height="371" alt="image" src="https://github.com/user-attachments/assets/0570498d-9977-43bf-b527-60141a002087" />

## 💻 Exercise 2: Virtual Classroom Manager

This is a terminal-based application designed to manage classes, students, and assignment submissions.

### Key Functional Requirements

* **Classroom Management:** Add, list, and manage virtual classrooms
* **Student Enrollment:** Enroll students by ID and name into existing classrooms
* **Assignment Scheduling:** Schedule assignments of different types (MCQ, Essay, Practical)
* **Assignment Submission:** Mark an assignment as submitted, which triggers system alerts

### Design Patterns Used (Integrated into App Core)

| Pattern | Component | Rationale |
|---------|-----------|-----------|
| **Singleton** | ClassroomManager | Ensures only one global instance exists to manage the state of all classrooms and students across the system |
| **Observer** | Student & Teacher | The Student (Subject) notifies all attached Teacher instances (Observers) immediately upon submitting an assignment, fulfilling the real-time "progress alert" use case |
| **Factory** | AssignmentFactory | Abstracts the object creation process for different assignment types (MCQ, ESSAY), making the system easily extensible without changing core manager logic |

---

## 🎨 Exercise 1: Design Pattern Demonstrations

The application provides six separate, isolated demonstrations, one for each required pattern category. These demonstrations use creative, education-aligned use cases.

### Pattern Implementation Overview

| Category | Pattern | Use Case | Demo File |
|----------|---------|----------|-----------|
| **Behavioral** | Observer | Student Progress Alerts | `ObserverDemo.java` |
| **Behavioral** | Strategy | Adaptive Quiz Difficulty Scoring | `StrategyDemo.java` |
| **Creational** | Singleton | Global Classroom Manager Instance | `SingletonDemo.java` |
| **Creational** | Factory | Task/Assignment Creation | `FactoryDemo.java` |
| **Structural** | Decorator | Assignment Analytics (Adding Time/Difficulty) | `DecoratorDemo.java` |
| **Structural** | Adapter | Legacy Student Data Integration | `AdapterDemo.java` |

### Behavioral Patterns

#### Observer Pattern
- **Use Case:** Student Progress Alerts
- **Implementation:** `ObserverDemo.java`
- **Description:** Demonstrates real-time notification system when students submit assignments

#### Strategy Pattern
- **Use Case:** Adaptive Quiz Difficulty Scoring
- **Implementation:** `StrategyDemo.java`
- **Description:** Shows different scoring strategies based on quiz difficulty levels

### Creational Patterns

#### Singleton Pattern
- **Use Case:** Global Classroom Manager Instance
- **Implementation:** `SingletonDemo.java`
- **Description:** Ensures a single point of access to classroom management functionality

#### Factory Pattern
- **Use Case:** Task/Assignment Creation
- **Implementation:** `FactoryDemo.java`
- **Description:** Demonstrates dynamic creation of different assignment types

### Structural Patterns

#### Decorator Pattern
- **Use Case:** Assignment Analytics (Adding Time/Difficulty)
- **Implementation:** `DecoratorDemo.java`
- **Description:** Shows how to add additional features to assignments dynamically

#### Adapter Pattern
- **Use Case:** Legacy Student Data Integration
- **Implementation:** `AdapterDemo.java`
- **Description:** Demonstrates integration of legacy student data formats with the current system
