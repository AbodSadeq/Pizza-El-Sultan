package org.example.pricing;

import org.example.model.Order;

public class SubtotalDiscount implements DiscountRule {

    private int percentage;
    private int minimumSubTotal;

    public SubtotalDiscount(int percentage , int minimumSubTotal){
        this.minimumSubTotal = minimumSubTotal;
        this.percentage = percentage;
    }
    @Override
    public boolean isApplication(Order order , int subTotal)
    {
        return minimumSubTotal < subTotal;
    }

    @Override
    public int calculateDiscount(Order order, int subTotal) {
        if(isApplication(order,subTotal)){
            return subTotal*percentage/100;
        }
        return 0;
    }
}
