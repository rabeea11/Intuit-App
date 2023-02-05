package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.model.PaymentDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Optional;

public interface IPaymentService {
    boolean sendPayment(Payment payment);
    boolean isValidPayment(Payment payment);

    boolean isValidCurrency(Payment payment);

    boolean isValidAmount(Payment payment);

    Optional<PaymentDTO> getPaymentById(String id);
    void deletePaymentById(String id);
    void deleteAll();
    ArrayList<PaymentDTO> getAllPaymentsFromDb();
}
