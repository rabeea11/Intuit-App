package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface IPaymentController {
    public ResponseEntity<Payment> sendMessage(@RequestBody Payment payment) throws Exception;
    }
