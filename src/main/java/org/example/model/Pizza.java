package org.example.model;

import java.util.ArrayList;

public class Pizza extends MenuItem {
    private PizzaSize size;
    private ArrayList<AddOn> addOns = new ArrayList<>();

    public Pizza(String name , int basePrice , boolean available , PizzaSize size){
        super(name,basePrice,available);
        this.size = size;
    }
    public void addAddOn(AddOn addOn){
        addOns.add(addOn);
    }

    @Override
    public int calculatePrice() {
        int totalPrice = basePrice;

        if(size == PizzaSize.MEDIUM){
            totalPrice +=30;
        }else if(size == PizzaSize.LARGE){
            totalPrice +=60;
        }

        for(AddOn temp : addOns){
            totalPrice += temp.getPrice();
        }

        return totalPrice;

    }
}
