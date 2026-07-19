package az.amin.techstore.wallet.service;

import az.amin.techstore.wallet.model.PaymentStartCommand;

public interface WalletService {
    void handlePayment(PaymentStartCommand paymentStartCommand);
}
