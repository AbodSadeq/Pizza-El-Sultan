package org.example.pricing;

import org.example.model.Order;
import org.example.model.OrderType;
import org.example.model.PriceSummary;

import java.util.ArrayList;

public class OrderPricingCalculator {
    private ArrayList<DiscountRule> discountRules;

    public void addDiscount(DiscountRule discountRule){
        discountRules.add(discountRule);
    }
    public OrderPricingCalculator(){
        discountRules = new ArrayList<>();
    }

    public PriceSummary calculate(Order order) {
        PriceSummary priceSummary =new PriceSummary();

        int tempSubTotal = order.calculateSubTotal();
        priceSummary.setSubtotal(tempSubTotal);
        priceSummary.setFulfillmentCharge(0);

        if(order.getOrderType() == OrderType.DELIVERY){
            priceSummary.setFulfillmentCharge(30);
        }


        int maxDiscount = 0;
        for(DiscountRule discount : discountRules){
            maxDiscount = Math.max(discount.calculateDiscount(order,tempSubTotal),maxDiscount);
        }
        priceSummary.setDiscount(maxDiscount);

        priceSummary.setFinalTotal(priceSummary.getSubtotal()+priceSummary.getFulfillmentCharge()-maxDiscount);

        return priceSummary;
    }
}
