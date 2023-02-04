package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPaymentDAO;
import com.example.IntuitApp.Interfaces.IPaymentService;
import com.example.IntuitApp.kafka.Producer;
import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.model.PaymentDTO;
import com.example.IntuitApp.valdiators.ValidateAmount;
import com.example.IntuitApp.valdiators.ValidateCurrency;
import com.example.IntuitApp.valdiators.ValidatePayment;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@Slf4j
public class PaymentService implements IPaymentService {

    @Autowired
    Producer producer;
    @Autowired
    ValidatePayment validatePayment;
    @Autowired
    ValidateAmount validateAmount;
    @Autowired
    ValidateCurrency validateCurrency;
    @Autowired
    private Gson gson;
    @Autowired
    IPaymentDAO paymentDAO;

    @Override
    public boolean sendPayment(Payment payment) {
        log.info("Sending event by Kafka");
        try {
//            String jsonMsg = gson.toJson(payment);
            producer.sendMessage(payment);
        }catch (JsonParseException ex){
            return false;
        }
        return true;
    }

    @Override
    public boolean isValidPayment(Payment payment) {
        return validatePayment.isValid(payment);
    }

    @Override
    public boolean isValidCurrency(Payment payment) {
        return validateCurrency.isValid(payment);
    }

    @Override
    public boolean isValidAmount(Payment payment) {
        return validateAmount.isValid(payment);
    }

    @Override
    public Optional<PaymentDTO> getPaymentById(String id) {
        if (paymentDAO.existsById(id))
            return paymentDAO.findById(id);

        return null;
    }

    @Override
    public ArrayList<PaymentDTO> getAllPaymentsFromDb() {
        return (ArrayList<PaymentDTO>) paymentDAO.findAll();
    }

}
