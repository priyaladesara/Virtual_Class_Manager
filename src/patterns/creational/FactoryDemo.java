package com.ei.classroom.patterns.creational;

import com.ei.classroom.model.Assignment;
import com.ei.classroom.factory.AssignmentFactory;
import com.ei.classroom.factory.AssignmentType;
import com.ei.classroom.util.AppLogger;

/**
 * FACTORY PATTERN DEMO: Task/Assignment Factory.
 * Demonstrates how AssignmentFactory abstracts object creation.
 */
public class FactoryDemo {
    public static void runDemo() {
        AppLogger.logInfo("FactoryDemo", "Starting Factory Pattern Demo: Task/Assignment Factory");
        
        try {
            // 1. Create an MCQ Assignment
            Assignment mcq = AssignmentFactory.createAssignment(
                AssignmentType.MCQ, "Unit 1 Quiz", "Science 7");
            System.out.println("Created: " + mcq.getDetails());

            // 2. Create an Essay Assignment
            Assignment essay = AssignmentFactory.createAssignment(
                AssignmentType.ESSAY, "Historical Analysis", "History 9");
            System.out.println("Created: " + essay.getDetails());
            
            // 3. Create a Practical Assignment
            Assignment practical = AssignmentFactory.createAssignment(
                AssignmentType.PRACTICAL, "Build a Robot", "Robotics Club");
            System.out.println("Created: " + practical.getDetails());
            
            // 4. Demonstrate extensibility (new type can be added easily in the factory)
            
        } catch (IllegalArgumentException e) {
            // Defensive programming
            AppLogger.logError("FactoryDemo", "Error during factory creation.", e);
        }
        
        AppLogger.logInfo("FactoryDemo", "Factory Pattern Demo Finished.\n");
    }
}
