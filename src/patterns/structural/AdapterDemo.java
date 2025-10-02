package com.ei.classroom.patterns.structural;

import com.ei.classroom.model.Student;
import com.ei.classroom.util.AppLogger;
import java.util.UUID;

// Target Interface (What the application expects)
interface StudentImporter {
    Student importStudent(String legacyData);
}

// Adaptee (The class/data format that needs adapting)
class LegacyCSVData {
    private final String csvRecord;

    public LegacyCSVData(String csvRecord) {
        this.csvRecord = csvRecord;
    }

    public String getCSVRecord() {
        return csvRecord;
    }
}

// Adapter (Converts the Adaptee to the Target Interface)
class LegacyDataAdapter implements StudentImporter {
    private static final String CLASS_NAME = "LegacyDataAdapter";

    @Override
    public Student importStudent(String legacyData) {
        // The core logic of the Adapter: converting the format
        try {
            // Assume CSV format: Name,ID
            String[] parts = legacyData.split(",");
            if (parts.length < 2) {
                throw new IllegalArgumentException("Invalid CSV format. Expected: Name,ID");
            }
            String name = parts[0].trim();
            String id = parts[1].trim();
            
            // Return the object the rest of the application uses
            AppLogger.logDebug(CLASS_NAME, "Successfully converted CSV for " + name);
            return new Student(id, name);
            
        } catch (Exception e) {
            AppLogger.logError(CLASS_NAME, "Failed to adapt legacy data: " + legacyData, e);
            // Defensive programming: return a placeholder or throw a custom exception
            return new Student(UUID.randomUUID().toString(), "ImportError");
        }
    }
}

/**
 * ADAPTER PATTERN DEMO: Legacy Data Integration.
 * Adapts legacy student data (CSV string) into the standard Student object 
 * used by the Virtual Classroom Manager.
 */
public class AdapterDemo {
    public static void runDemo() {
        AppLogger.logInfo("AdapterDemo", "Starting Adapter Pattern Demo: Legacy Data Integration");

        // 1. The legacy data format
        String legacyRecord1 = "Bob Smith,BS123";
        String legacyRecord2 = "Jane Doe,JD456";
        
        // 2. The adapter implementation
        StudentImporter adapter = new LegacyDataAdapter();
        
        // 3. Use the adapter to import the data
        Student student1 = adapter.importStudent(legacyRecord1);
        Student student2 = adapter.importStudent(legacyRecord2);
        
        System.out.println("Imported Student 1 (Adapted): ID=" + student1.getId() + ", Name=" + student1.getName());
        System.out.println("Imported Student 2 (Adapted): ID=" + student2.getId() + ", Name=" + student2.getName());

        AppLogger.logInfo("AdapterDemo", "Adapter Pattern Demo Finished.\n");
    }
}
