package com.ei.classroom.patterns.behavioral;

import com.ei.classroom.model.Assignment;
import com.ei.classroom.model.Student;
import com.ei.classroom.observer.Teacher;
import com.ei.classroom.util.AppLogger;

/**
 * OBSERVER PATTERN DEMO: Student Progress Alerts.
 * The Teacher (Observer) is notified when a Student (Subject) takes an action (submits an assignment).
 */
public class ObserverDemo {
    public static void runDemo() {
        AppLogger.logInfo("ObserverDemo", "Starting Observer Pattern Demo: Student Progress Alerts");
        
        // 1. Create a Subject (Student)
        Student alice = new Student("S101", "Alice Johnson");
        
        // 2. Create Observers (Teachers/Parents)
        Teacher mrSmith = new Teacher("Mr. Smith (Teacher)");
        Teacher msJones = new Teacher("Ms. Jones (Teacher)");
        
        // 3. Attach Observers to the Subject
        alice.attach(mrSmith);
        alice.attach(msJones);
        
        // 4. Create an action (State change in Subject)
        Assignment essay = new Assignment("Final Essay", "English 101", "ESSAY");
        
        System.out.println("--- Action: Alice submits the Final Essay ---");
        // This action triggers the notifyObservers() call inside Student.submitAssignment()
        alice.submitAssignment(essay);
        
        System.out.println("\n--- Action: Detaching one Observer ---");
        alice.detach(msJones);
        
        Assignment quiz = new Assignment("Midterm Quiz", "Math 101", "MCQ");
        System.out.println("--- Action: Alice submits the Midterm Quiz ---");
        alice.submitAssignment(quiz); // Only Mr. Smith should receive the second notification
        
        AppLogger.logInfo("ObserverDemo", "Observer Pattern Demo Finished.\n");
    }
}
