package org.example.notification;

import org.example.model.Order;
import org.example.model.OrderStatus;

public class WhatsAppNotification implements NotificationChannel {
    @Override
    public void send(Order order) {
        if(order.getStatus() == OrderStatus.READY){
            System.out.println("WhatsApp message to " + order.getCustomer().getPhone()+":\n" +
                      "Hello "+ order.getCustomer().getName() +", your order #"+ order.getId()+" is ready.\n" +
                      "Thank you for ordering from Pizza El Sultan.\n");
            order.completed();
        }
    }
}
