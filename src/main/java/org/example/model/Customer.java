package org.example.model;

public class Customer {
    private String name;
    private String phone;
    private String address;

    public Customer(String name , String phone , String address){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }
}
