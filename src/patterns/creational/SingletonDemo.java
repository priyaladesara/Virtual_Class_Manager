package com.ei.classroom.patterns.creational;

import com.ei.classroom.manager.ClassroomManager;
import com.ei.classroom.util.AppLogger;

/**
 * SINGLETON PATTERN DEMO: Classroom Manager.
 * Demonstrates that only one instance of ClassroomManager is ever created.
 */
public class SingletonDemo {
    public static void runDemo() {
        AppLogger.logInfo("SingletonDemo", "Starting Singleton Pattern Demo: Classroom Manager");
        
        // 1. Request the first instance
        System.out.println("Requesting Instance 1 (Should trigger initialization):");
        ClassroomManager manager1 = ClassroomManager.getInstance();
        
        // 2. Request the second instance
        System.out.println("\nRequesting Instance 2 (Should NOT trigger re-initialization):");
        ClassroomManager manager2 = ClassroomManager.getInstance();
        
        // 3. Compare the instances
        boolean areSame = (manager1 == manager2);
        
        System.out.println("\nManager 1 HashCode: " + manager1.hashCode());
        System.out.println("Manager 2 HashCode: " + manager2.hashCode());
        System.out.println("Are both instances the same object? " + areSame);
        
        if (areSame) {
            System.out.println("Success: Only a single instance of ClassroomManager exists (Singleton upheld).");
        } else {
            System.out.println("Failure: Multiple instances created (Singleton failed).");
        }
        
        // Verify state persistence
        manager1.addClassroom("DemoClassA");
        System.out.print("Checking manager2 state: ");
        manager2.listClassrooms();
        
        AppLogger.logInfo("SingletonDemo", "Singleton Pattern Demo Finished.\n");
    }
}
