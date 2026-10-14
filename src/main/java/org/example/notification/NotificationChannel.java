package org.example.notification;

import org.example.model.Order;

public interface NotificationChannel {
    public void send(Order order);
}
