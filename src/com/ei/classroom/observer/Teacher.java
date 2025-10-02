package com.ei.classroom.observer;

import com.ei.classroom.util.AppLogger;


public class Teacher implements NotificationObserver {
    private static final String CLASS_NAME = "Teacher";
    private final String name;

    public Teacher(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void update(String message) {
        AppLogger.logInfo(CLASS_NAME, "Notification for " + name + ": " + message);
    }
}
