package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPayeeService;
import com.example.IntuitApp.Interfaces.IPaymentDAO;
import com.example.IntuitApp.model.Payee;
import com.example.IntuitApp.model.PaymentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PayeeService implements IPayeeService {

    @Autowired
    IPaymentDAO paymentDAO;

    @Override
    public ArrayList<Payee> getAllPayeesFromDb() {
        ArrayList<Payee> payees = new ArrayList<>();
        for(PaymentDTO payment: paymentDAO.findAll()){
            payees.add(getPayeeById(payment.getPayeeid()));
        }
        return payees;
    }

    private Payee getPayeeById(String payeeid) {
        return new Payee(payeeid,"Rabia", "Ashqar", "rabia.ash@gmail.com");
    }

}
