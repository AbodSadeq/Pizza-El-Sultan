package org.example.pricing;

import org.example.model.Order;

public interface DiscountRule {
    public boolean isApplication(Order order , int subTotal);
    public int calculateDiscount(Order order , int subTotal);
}
