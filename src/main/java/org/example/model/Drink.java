package org.example.model;

public class Drink extends MenuItem {

    public Drink(String name , int basePrice , boolean available){
        super(name,basePrice,available);
    }

    @Override
    public int calculatePrice() {
        return basePrice;
    }
}
