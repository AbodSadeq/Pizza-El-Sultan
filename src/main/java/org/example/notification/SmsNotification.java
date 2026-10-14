package org.example.notification;

import org.example.model.Order;
import org.example.model.OrderStatus;

public class SmsNotification implements NotificationChannel {
    @Override
    public void send(Order order) {
        if(order.getStatus() == OrderStatus.READY){
            System.out.println("SMS sent to "+ order.getCustomer().getPhone()+":\n" +
                    "Your order #"+order.getId()+" is ready.");
            order.completed();
        }
    }
}
