package org.example.model;

import org.example.menu.Menu;
import org.example.payment.Payment;

import java.util.ArrayList;

public class Order {
//    private static int idAll = 0;
    private int id ;
    private Customer customer;
    private OrderType orderType;
    private OrderStatus status;
    private ArrayList<OrderItem> items = new ArrayList<>();
    private Payment payment;

    public Order(int id ,Customer customer,OrderType orderType){
        this.id = id;
        this.customer = customer;
        this.orderType = orderType;
        this.status = OrderStatus.NEW;
    }

    public void addItem(OrderItem item , Menu menu){
        if(menu.isAvailable(item.getItem().getName())){
            items.add(item);
        }
    }

    public int calculateSubTotal(){
        int subTotal = 0;
        for(OrderItem temp : items){
            subTotal += temp.calculateTotal();
        }
        return subTotal;
    }

    public void startPreparing(){
        status = OrderStatus.PREPARING;
    }

    public void markReady(){
        status = OrderStatus.READY;
    }

    public void completed(){
        status = OrderStatus.COMPLETED;
    }

    public void canceling(){
        status = OrderStatus.CANCELED;
    }


    // getter
    public Customer getCustomer() {
        return customer;
    }

    public int getId() {
        return id;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public ArrayList<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    // setter
    public void setPayment(Payment payment) {
        this.payment = payment;
        if(payment.isSuccessful()){
            startPreparing();
        }else {
            status = OrderStatus.CANCELED;
        }
    }
}
