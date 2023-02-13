package com.example.IntuitApp.controllers;


import com.example.IntuitApp.model.Payee;
import com.example.IntuitApp.services.PayeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@Slf4j
@RestController
public class PayeeController {

    @Autowired
    PayeeService payeeService;

    @GetMapping(value = "payees")
    public ResponseEntity<ArrayList<Payee>> getAllPayees(){
        ArrayList<Payee> payees = payeeService.getAllPayeesFromDb();
        if(payees.size() > 0)
            return  new ResponseEntity<>(payees, HttpStatus.OK);
        else
            return ResponseEntity.noContent().build();
    }
}
