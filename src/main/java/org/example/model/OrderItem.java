package org.example.model;

public class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity){
        if(quantity <= 0){
            throw new IllegalArgumentException(
                    "Quantity must be greater than zero"
            );
        }
            this.quantity = quantity;
        this.item= item;
    }
    public int calculateTotal(){
        return item.calculatePrice()*quantity ;
    }

    public int getQuantity() {
            return quantity;
    }

    public MenuItem getItem() {
        return item;
    }


}
