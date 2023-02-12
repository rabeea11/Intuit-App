package com.example.IntuitApp.controllers;


import com.example.IntuitApp.Interfaces.IPaymentMethodService;
import com.example.IntuitApp.model.PaymentMethod;
import com.example.IntuitApp.services.PaymentMethodService;
import com.example.IntuitApp.services.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
public class PayMethodController {

    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping(value = "paymentMethods")
    public ResponseEntity<ArrayList<PaymentMethod>> getAllMethodsIds(){
        return  new ResponseEntity<>(paymentMethodService.getAllPaymentMethodsFromDb(),HttpStatus.OK);
    }
}
