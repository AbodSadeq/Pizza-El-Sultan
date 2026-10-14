package org.example.model;

public abstract class MenuItem {
    protected String name;
    protected int basePrice;
    protected boolean available;

    public MenuItem(String name , int basePrice , boolean available){
        this.available = available;
        this.basePrice= basePrice;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public boolean isAvailable() { return available; }

    public int getBasePrice() {
        return basePrice;
    }

    public void makeAvailable(){
        available = true;
    }

    public void makeUnavailable(){
        available = false;
    }

    public int calculatePrice(){
        return 0;
    }
}
