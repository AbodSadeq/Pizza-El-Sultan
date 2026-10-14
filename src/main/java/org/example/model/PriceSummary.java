package org.example.model;

public class PriceSummary {
    private int subtotal;
    private int discount;
    private int fulfillmentCharge; // delivery => 30    || takeaway => 0
    private int finalTotal;

    public int getDiscount() {
        return discount;
    }

    public int getFinalTotal() {
        return finalTotal;
    }

    public int getFulfillmentCharge() {
        return fulfillmentCharge;
    }

    public int getSubtotal() {
        return subtotal;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public void setFinalTotal(int finalTotal) {
        this.finalTotal = finalTotal;
    }

    public void setFulfillmentCharge(int fulfillmentCharge) {
        this.fulfillmentCharge = fulfillmentCharge;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }
}
