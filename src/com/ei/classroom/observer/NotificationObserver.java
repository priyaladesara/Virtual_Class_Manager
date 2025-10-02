package com.ei.classroom.observer;

public interface NotificationObserver {
    /**
     * Action to be taken when the subject state changes.
     * @param message The notification message.
     */
    void update(String message);
}