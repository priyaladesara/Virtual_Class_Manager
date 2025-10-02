package com.ei.classroom.model;

import com.ei.classroom.observer.NotificationObserver;
import com.ei.classroom.observer.NotificationSystem;
import com.ei.classroom.util.AppLogger;
import java.util.ArrayList;
import java.util.List;


public class Student implements NotificationSystem {
    private static final String CLASS_NAME = "Student";
    private final String id;
    private final String name;
    private final List<NotificationObserver> observers; // Teachers/Parents
    private final List<Assignment> submittedAssignments;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
        this.observers = new ArrayList<>();
        this.submittedAssignments = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    // --- Observer Pattern Implementation (Subject) ---

    @Override
    public void attach(NotificationObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            AppLogger.logDebug(CLASS_NAME, name + " now observed by " + observer.getClass().getSimpleName());
        }
    }

    @Override
    public void detach(NotificationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        AppLogger.logDebug(CLASS_NAME, "Notifying observers: " + message);
        for (NotificationObserver observer : observers) {
            observer.update(message);
        }
    }
    
    // --- Core Logic ---

    public void submitAssignment(Assignment assignment) {
        assignment.setSubmitted(true);
        submittedAssignments.add(assignment);
        
        // Notify observers about the submission event
        String message = String.format("Assignment '%s' submitted by Student [%s] in class %s.", 
                                        assignment.getName(), this.id, assignment.getClassName());
        this.notifyObservers(message);
    }

    public List<Assignment> getSubmittedAssignments() {
        return submittedAssignments;
    }
}
