package org.example.payment;

public class CardPayment extends Payment implements ElectronicRefund {
    public CardPayment(int amount )
    {
        super(amount);
    }

    @Override
    public PaymentStatus pay() {
        status = PaymentStatus.SUCCESS;
        return status;
    }

    @Override
    public boolean refund() {
        return status == PaymentStatus.SUCCESS;
    }
}
