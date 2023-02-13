package com.example.IntuitApp.controllers;

import com.example.IntuitApp.Interfaces.IPaymentController;
import com.example.IntuitApp.model.*;
import com.example.IntuitApp.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@Slf4j
@RestController
@RequestMapping("/payments")
public class PaymentController implements IPaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value="/payment")
    public ResponseEntity<Payment> sendMessage(@RequestBody Payment payment) throws Exception {
        log.info("Create new Payment request: {}" , payment);
        if(!paymentService.isValidPayment(payment)){
            log.error(Constants.WRONG_PAYMENT);
            payment.setMessage(Constants.WRONG_PAYMENT);
            return ResponseEntity.badRequest().body(payment);
        }
        if(!paymentService.isValidAmount(payment)){
            log.error(Constants.WRONG_AMOUNT);
            payment.setMessage(Constants.WRONG_AMOUNT);

            return ResponseEntity.badRequest().body(payment);
        }
        if(!paymentService.isValidCurrency(payment)){
            log.error(Constants.WRONG_CURRENCY);
            payment.setMessage(Constants.WRONG_CURRENCY);
            return ResponseEntity.badRequest().body(payment);
        }

        if(paymentService.sendPayment(payment)) {
            log.info(Constants.PAYMENT_SENT);
        }else{
            log.error(Constants.INVALID_JSON);
        }
        payment.setMessage(Constants.SUCCESSFULLY_SENT);
        return new ResponseEntity<>(payment, HttpStatus.CREATED);
    }

    @GetMapping(value = "payment/{id}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable String id){
        if (paymentService.getPaymentById(id)!= null) {
            PaymentDTO paymentDTO=paymentService.getPaymentById(id);
            log.info("Get Payment from DB: {}",paymentDTO);
            return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
        }
        else {
            log.error("Payment with the provided ID  {} Does not Exist in DB", id);
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @GetMapping(value = "allPayments")
    public ResponseEntity<ArrayList<PaymentDTO>> getAllPayments(){
        ArrayList<PaymentDTO> payments = paymentService.getAllPaymentsFromDb();
        if(payments.size()==0)
          return ResponseEntity.noContent().build();
        return new ResponseEntity<>(payments,HttpStatus.OK);
    }

    @DeleteMapping(value = "deleteAllPayments")
    public void deletePayments(){
        paymentService.deleteAll();
    }

    @DeleteMapping(value = "deletePayment/{id}")
    public void deletePayment(@PathVariable String id){
        paymentService.deletePaymentById(id);
    }

}
