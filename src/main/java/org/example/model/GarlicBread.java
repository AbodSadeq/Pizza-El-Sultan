package org.example.model;

public class GarlicBread extends MenuItem {
    public GarlicBread(String name , int basePrice , boolean available){
        super(name,basePrice,available);
    }

    @Override
    public int calculatePrice() {
        return basePrice;
    }
}
