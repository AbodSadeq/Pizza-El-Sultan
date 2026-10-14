package org.example.pricing;

import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Pizza;

public class PizzaDiscount implements DiscountRule {

    private int percentage;
    private int minimumSubTotal;

    public PizzaDiscount(int percentage , int minimumSubTotal){
        this.minimumSubTotal = minimumSubTotal;
        this.percentage = percentage;
    }

    @Override
    public boolean isApplication(Order order, int subTotal) {
        return ((subTotal > minimumSubTotal) && containsPizza(order));
    }

    public boolean containsPizza(Order order){

        int numOfPizza = 0;
        for (OrderItem item : order.getItems()){
            if(item.getItem() instanceof Pizza){
                numOfPizza += item.getQuantity();
            }
        }
        return numOfPizza>=2;
    }

    @Override
    public int calculateDiscount(Order order, int subTotal) {
        if(isApplication(order,subTotal)){
            return subTotal*percentage/100;
        }
        return 0;
    }
}
