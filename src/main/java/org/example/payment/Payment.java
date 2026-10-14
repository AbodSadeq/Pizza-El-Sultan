package org.example.payment;

public abstract class Payment {
    protected int amount;
    protected PaymentStatus status;

    public Payment(int amount){
        this.amount = amount;
        status = PaymentStatus.PENDING;
    }
    public PaymentStatus pay(){
        return PaymentStatus.PENDING;
    }

    public boolean isSuccessful(){
        return status == PaymentStatus.SUCCESS;
    }

}
