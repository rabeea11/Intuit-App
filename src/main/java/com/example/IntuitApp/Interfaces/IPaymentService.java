package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.Payment;

public interface IPaymentService {
    boolean sendPayment(Payment payment);
    boolean isValidPayment(Payment payment);

    boolean isValidCurrency(Payment payment);

    boolean isValidAmount(Payment payment);
}
