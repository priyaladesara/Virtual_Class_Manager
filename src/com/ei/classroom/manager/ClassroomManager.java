package com.ei.classroom.manager;

import com.ei.classroom.model.Assignment;
import com.ei.classroom.model.Classroom;
import com.ei.classroom.model.Student;
import com.ei.classroom.observer.Teacher;
import com.ei.classroom.factory.AssignmentFactory;
import com.ei.classroom.factory.AssignmentType;
import com.ei.classroom.util.AppLogger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * ClassroomManager implements the Singleton Pattern and is the core controller.
 */
public class ClassroomManager {
    private static final String CLASS_NAME = "ClassroomManager";
    
    // --- Singleton Implementation ---
    private static ClassroomManager instance;

    private final Map<String, Classroom> classrooms;
    private final Map<String, Student> students;
    private final List<Teacher> teachers;

    /**
     * Private constructor.
     */
    private ClassroomManager() {
        this.classrooms = new HashMap<>();
        this.students = new HashMap<>();
        this.teachers = new ArrayList<>();
        teachers.add(new Teacher("Admin Teacher")); 
        AppLogger.logInfo(CLASS_NAME, "System initialized (Singleton instance created).");
    }

    /**
     * Public access point for the Singleton instance.
     */
    public static ClassroomManager getInstance() {
        if (instance == null) {
            instance = new ClassroomManager();
        }
        return instance;
    }
    
    // --- Core Management Methods ---

    public void addClassroom(String name) {
        if (classrooms.containsKey(name)) {
            AppLogger.logError(CLASS_NAME, "Classroom '" + name + "' already exists.");
            return;
        }
        classrooms.put(name, new Classroom(name));
        System.out.println("Classroom [" + name + "] has been created.");
        AppLogger.logInfo(CLASS_NAME, "Classroom " + name + " added.");
    }
    
    /**
     * Enrolls a student into a classroom.
     */
    public void enrollStudent(String studentId, String studentName, String className) throws NoSuchElementException {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            throw new NoSuchElementException("Classroom [" + className + "] not found.");
        }

        Student student = students.computeIfAbsent(studentId, k -> {
            AppLogger.logInfo(CLASS_NAME, "New student created: " + studentName + " (" + studentId + ")");
            Student newStudent = new Student(studentId, studentName);
            teachers.forEach(newStudent::attach); // Attach observers
            return newStudent;
        });

        classroom.enrollStudent(student);
        System.out.println("Student [" + studentId + " - " + studentName + "] has been enrolled in [" + className + "].");
    }

    /**
     * Schedules an assignment (Factory Pattern).
     */
    public void scheduleAssignment(String className, String assignmentName, String typeString) 
            throws IllegalArgumentException, NoSuchElementException {
        
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            throw new NoSuchElementException("Classroom [" + className + "] not found.");
        }

        try {
            AssignmentType type = AssignmentType.valueOf(typeString.toUpperCase());
            Assignment assignment = AssignmentFactory.createAssignment(type, assignmentName, className);
            classroom.getAssignments().add(assignment);
            System.out.println("Assignment for [" + className + "] has been scheduled: " + assignment.getType());
            AppLogger.logInfo(CLASS_NAME, "Assignment scheduled: " + assignmentName);
            
        } catch (IllegalArgumentException e) {
            AppLogger.logError(CLASS_NAME, "Invalid assignment type provided: " + typeString, e);
            throw new IllegalArgumentException("Invalid assignment type. Use: MCQ, ESSAY, or PRACTICAL.");
        }
    }
    
    /**
     * Submits an assignment (Observer Pattern notification).
     */
    public void submitAssignment(String studentId, String className, String assignmentName) 
            throws NoSuchElementException {
        
        Student student = students.get(studentId);
        Classroom classroom = classrooms.get(className);

        if (student == null) {
            throw new NoSuchElementException("Student [" + studentId + "] not found.");
        }
        if (classroom == null) {
            throw new NoSuchElementException("Classroom [" + className + "] not found.");
        }

        Assignment assignmentToSubmit = classroom.getAssignments().stream()
                .filter(a -> a.getName().equalsIgnoreCase(assignmentName) && !a.isSubmitted())
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Assignment '" + assignmentName + "' not found or already submitted in class [" + className + "]."));
        
        student.submitAssignment(assignmentToSubmit); 
        
        System.out.println("Assignment submitted by Student [" + studentId + "] in [" + className + "].");
    }
    
    // --- Listing Methods ---
    
    public void listClassrooms() {
        if (classrooms.isEmpty()) {
            System.out.println("No classrooms created yet.");
            return;
        }
        System.out.println("\n--- Active Classrooms ---");
        classrooms.values().forEach(c -> {
            System.out.printf("- %s (%d students)\n", c.getName(), c.getEnrolledStudents().size());
        });
        System.out.println("-------------------------\n");
    }
    
    public void listStudentsInClass(String className) throws NoSuchElementException {
        Classroom classroom = classrooms.get(className);
        if (classroom == null) {
            throw new NoSuchElementException("Classroom [" + className + "] not found.");
        }
        
        List<Student> students = classroom.getEnrolledStudents();
        if (students.isEmpty()) {
            System.out.println("No students enrolled in [" + className + "].");
            return;
        }
        
        System.out.println("\n--- Students in [" + className + "] ---");
        students.forEach(s -> System.out.printf("ID: %s | Name: %s\n", s.getId(), s.getName()));
        System.out.println("-------------------------------------\n");
    }
}