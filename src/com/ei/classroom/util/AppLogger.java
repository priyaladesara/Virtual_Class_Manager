package com.ei.classroom.util;

public class AppLogger {
    private static final String INFO_PREFIX = "[INFO] ";
    private static final String ERROR_PREFIX = "[ERROR] ";
    private static final String DEBUG_PREFIX = "[DEBUG] ";

    public static void logInfo(String className, String message) {
        System.out.println(INFO_PREFIX + className + ": " + message);
    }

    public static void logError(String className, String message) {
        System.err.println(ERROR_PREFIX + className + ": " + message);
    }

    public static void logError(String className, String message, Exception e) {
        System.err.println(ERROR_PREFIX + className + ": " + message + " -> " + e.getMessage());
    }

    public static void logDebug(String className, String message) {
        // Debug logging is intentionally commented out for a cleaner console in the demo.
        // System.out.println(DEBUG_PREFIX + className + ": " + message);
    }
}
