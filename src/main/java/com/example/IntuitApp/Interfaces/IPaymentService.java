package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.model.PaymentDTO;
import java.util.ArrayList;

public interface IPaymentService {
    boolean sendPayment(Payment payment);

    PaymentDTO getPaymentById(String id);
    void deletePaymentById(String id);
    void deleteAll();
    ArrayList<PaymentDTO> getAllPaymentsFromDb();

    }
