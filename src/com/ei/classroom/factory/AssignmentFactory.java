package com.ei.classroom.factory;

import com.ei.classroom.model.Assignment;
import com.ei.classroom.util.AppLogger;

public class AssignmentFactory {
    private static final String CLASS_NAME = "AssignmentFactory";

    public static Assignment createAssignment(AssignmentType type, String name, String className) {
        AppLogger.logDebug(CLASS_NAME, "Creating new assignment of type: " + type.name());
        
        switch (type) {
            case MCQ:
                return new Assignment(name, className, "Multiple Choice Quiz");
            case ESSAY:
                return new Assignment(name, className, "Essay Submission");
            case PRACTICAL:
                return new Assignment(name, className, "Practical Project");
            default:
                AppLogger.logError(CLASS_NAME, "Unsupported AssignmentType: " + type.name());
                throw new IllegalArgumentException("Unsupported assignment type: " + type.name());
        }
    }
}