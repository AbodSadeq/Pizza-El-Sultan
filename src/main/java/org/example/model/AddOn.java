package org.example.model;

public class AddOn {
    private String name;
    private int price;

    public AddOn(String name, int price){
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    public int getPrice(){
        return price;
    }
}
