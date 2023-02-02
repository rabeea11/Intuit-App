package com.example.IntuitApp.controllers;

import com.example.IntuitApp.Interfaces.IPaymentController;
import com.example.IntuitApp.kafka.Producer;
import com.example.IntuitApp.model.Constants;
import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.services.PaymentService;
import com.example.IntuitApp.valdiators.ValidateAmount;
import com.example.IntuitApp.valdiators.ValidateCurrency;
import com.example.IntuitApp.valdiators.ValidatePayment;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController implements IPaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value="/payment")
    public ResponseEntity<String> sendMessage(@RequestBody Payment payment) {
        log.info("Create new Payment request: {}" , payment);
        if(!paymentService.isValidPayment(payment)){
            log.error(Constants.WRONG_PAYMENT);
            return ResponseEntity.badRequest().body(Constants.WRONG_PAYMENT);
        }
        if(!paymentService.isValidAmount(payment)){
            log.error(Constants.WRONG_AMOUNT);
            return ResponseEntity.badRequest().body(Constants.WRONG_AMOUNT);
        }
        if(!paymentService.isValidCurrency(payment)){
            log.error(Constants.WRONG_CURRENCY);
            return ResponseEntity.badRequest().body(Constants.WRONG_CURRENCY);
        }

        if(paymentService.sendPayment(payment)) {
            log.info(Constants.PAYMENT_SENT);
        }else{
            log.error(Constants.INVALID_JSON);
        }
        return new ResponseEntity<>(Constants.SUCCESSFULLY_SENT, HttpStatus.OK);
    }

}
