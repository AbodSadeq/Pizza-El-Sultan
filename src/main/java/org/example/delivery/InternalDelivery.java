package org.example.delivery;

import org.example.model.Order;
import org.example.model.OrderStatus;

public class InternalDelivery extends DeliveryService {
    public InternalDelivery(){
        status = DeliveryStatus.NOT_ASSIGNED;
    }


    public void assign(Order order){
        if(status == DeliveryStatus.NOT_ASSIGNED && order.getStatus()== OrderStatus.READY){
            status = DeliveryStatus.ASSIGNED;
        }
    }
}
