package com.ei.classroom.observer;

public interface NotificationSystem {
    void attach(NotificationObserver observer);

    void detach(NotificationObserver observer);

    void notifyObservers(String message);
}