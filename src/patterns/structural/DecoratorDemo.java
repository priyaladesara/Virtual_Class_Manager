package com.ei.classroom.patterns.structural;

import com.ei.classroom.model.Assignment;
import com.ei.classroom.util.AppLogger;

// Base Assignment Interface (Component)
interface AssignmentComponent {
    String getName();
    String getDetails();
}

// Concrete Component: Wraps the existing Assignment model
class BasicAssignmentComponent implements AssignmentComponent {
    private final Assignment assignment;
    
    public BasicAssignmentComponent(Assignment assignment) {
        this.assignment = assignment;
    }
    
    @Override
    public String getName() {
        return assignment.getName();
    }

    @Override
    public String getDetails() {
        return assignment.getDetails();
    }
}

// Abstract Decorator
abstract class AssignmentDecorator implements AssignmentComponent {
    protected AssignmentComponent decoratedAssignment;
    
    public AssignmentDecorator(AssignmentComponent decoratedAssignment) {
        this.decoratedAssignment = decoratedAssignment;
    }

    // Delegation to the wrapped component
    @Override
    public String getName() {
        return decoratedAssignment.getName();
    }
    
    // Default implementation just calls the decorated component's method
    @Override
    public String getDetails() {
        return decoratedAssignment.getDetails();
    }
}

// Concrete Decorator 1: Add Time Tracking
class TimeTrackingDecorator extends AssignmentDecorator {
    public TimeTrackingDecorator(AssignmentComponent decoratedAssignment) {
        super(decoratedAssignment);
    }
    
    private String addTimeTracking() {
        // Simulate a tracked time
        return " | Time Limit: 60 mins";
    }

    @Override
    public String getDetails() {
        return decoratedAssignment.getDetails() + addTimeTracking();
    }
}

// Concrete Decorator 2: Add Difficulty Tag
class DifficultyTagDecorator extends AssignmentDecorator {
    private final String difficulty;
    
    public DifficultyTagDecorator(AssignmentComponent decoratedAssignment, String difficulty) {
        super(decoratedAssignment);
        this.difficulty = difficulty;
    }
    
    private String addDifficultyTag() {
        return " | Difficulty: " + difficulty;
    }

    @Override
    public String getDetails() {
        return decoratedAssignment.getDetails() + addDifficultyTag();
    }
}


/**
 * DECORATOR PATTERN DEMO: Assignment Analytics.
 * Wraps a basic Assignment with optional features like Time Tracking and Difficulty Tagging 
 * without modifying the original Assignment class.
 */
public class DecoratorDemo {
    public static void runDemo() {
        AppLogger.logInfo("DecoratorDemo", "Starting Decorator Pattern Demo: Assignment Analytics");

        // 1. Create a basic assignment object
        Assignment baseAssignment = new Assignment("Geography Quiz", "Geo 8", "MCQ");
        
        // 2. Wrap it in the Component interface
        AssignmentComponent quiz = new BasicAssignmentComponent(baseAssignment);
        
        System.out.println("1. Basic Assignment: " + quiz.getDetails());
        
        // 3. Decorate it with Time Tracking
        AssignmentComponent timedQuiz = new TimeTrackingDecorator(quiz);
        System.out.println("2. Timed Assignment: " + timedQuiz.getDetails());

        // 4. Decorate it further (stacking decorators) with Difficulty Tag
        AssignmentComponent advancedQuiz = new DifficultyTagDecorator(timedQuiz, "Expert");
        System.out.println("3. Advanced Assignment: " + advancedQuiz.getDetails());
        
        // 5. Create a different one with only the difficulty tag
        Assignment finalProject = new Assignment("Capstone Project", "Graduation", "PRACTICAL");
        AssignmentComponent finalProjectDecorated = 
                new DifficultyTagDecorator(new BasicAssignmentComponent(finalProject), "High");
        System.out.println("4. Final Project: " + finalProjectDecorated.getDetails());
        
        AppLogger.logInfo("DecoratorDemo", "Decorator Pattern Demo Finished.\n");
    }
}
