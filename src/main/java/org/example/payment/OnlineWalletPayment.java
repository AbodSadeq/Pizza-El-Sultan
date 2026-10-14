package org.example.payment;

public class OnlineWalletPayment extends Payment implements ElectronicRefund {
    private String walletPhone;

    public OnlineWalletPayment(int amount, String walletPhone){
        super(amount);
        this.walletPhone = walletPhone;
    }

    @Override
    public PaymentStatus pay() {
        if(walletPhone.length() == 11){
            status = PaymentStatus.SUCCESS;
        }else{
            status = PaymentStatus.FAILED;
        }
        return status;
    }

    @Override
    public boolean refund() {
        return status == PaymentStatus.SUCCESS && walletPhone.length() == 11;
    }

}


