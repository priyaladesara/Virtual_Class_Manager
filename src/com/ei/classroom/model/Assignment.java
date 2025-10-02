package com.ei.classroom.model;

public class Assignment {
    private final String name;
    private final String className;
    private final String type;
    private boolean isSubmitted;

    public Assignment(String name, String className, String type) {
        this.name = name;
        this.className = className;
        this.type = type;
        this.isSubmitted = false;
    }

    public String getName() {
        return name;
    }

    public String getClassName() {
        return className;
    }

    public String getType() {
        return type;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public String getDetails() {
        return String.format("%s (%s) for class %s. Status: %s.",
                name, type, className, isSubmitted ? "Submitted" : "Pending");
    }
}