package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPaymentService;
import com.example.IntuitApp.kafka.Producer;
import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.valdiators.ValidateAmount;
import com.example.IntuitApp.valdiators.ValidateCurrency;
import com.example.IntuitApp.valdiators.ValidatePayment;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

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

}
