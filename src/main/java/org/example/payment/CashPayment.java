package org.example.payment;

public class CashPayment extends Payment {

    private int amountReceived;
    private int change;

    public CashPayment(int amount , int amountReceived){
        super(amount);
        this.amountReceived = amountReceived;
        change = 0;
    }

    @Override
    public PaymentStatus pay(){
        if(amountReceived >= amount){
            change = amountReceived - amount;
            status = PaymentStatus.SUCCESS;
        }else{
            status = PaymentStatus.FAILED;
        }

        return status;
    }

    public int getChange() {
        return change;
    }


}
