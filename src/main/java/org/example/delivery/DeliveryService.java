package org.example.delivery;

public abstract class   DeliveryService {
    protected DeliveryStatus status;

    public DeliveryStatus track(){return status;};
    public int estimateDeliveryTime(){return 30;};

}
