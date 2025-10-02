package com.ei.classroom.model;

import com.ei.classroom.util.AppLogger;
import java.util.ArrayList;
import java.util.List;

/**
 * Classroom entity, containing lists of students and assignments.
 */
public class Classroom {
    private static final String CLASS_NAME = "Classroom";
    private final String name;
    private final List<Student> enrolledStudents;
    private final List<Assignment> assignments;

    public Classroom(String name) {
        this.name = name;
        this.enrolledStudents = new ArrayList<>();
        this.assignments = new ArrayList<>();
        AppLogger.logDebug(CLASS_NAME, "New classroom created: " + name);
    }

    public String getName() {
        return name;
    }

    public List<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void enrollStudent(Student student) {
        if (!enrolledStudents.contains(student)) {
            enrolledStudents.add(student);
            AppLogger.logInfo(CLASS_NAME, "Student " + student.getId() + " enrolled in " + name);
        } else {
            AppLogger.logInfo(CLASS_NAME, "Student " + student.getId() + " is already enrolled in " + name);
        }
    }
}
