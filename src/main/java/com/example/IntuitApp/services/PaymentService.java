package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPaymentDAO;
import com.example.IntuitApp.Interfaces.IPaymentService;
import com.example.IntuitApp.kafka.Producer;
import com.example.IntuitApp.model.Payee;
import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.model.PaymentDTO;
import com.example.IntuitApp.model.PaymentMethod;
import com.example.IntuitApp.valdiators.ValidateAmount;
import com.example.IntuitApp.valdiators.ValidateCurrency;
import com.example.IntuitApp.valdiators.ValidatePayment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class PaymentService implements IPaymentService {

    @Autowired
    private Producer producer;
    private ValidatePayment validatePayment = new ValidatePayment();
    private ValidateAmount validateAmount = new ValidateAmount();
    private ValidateCurrency validateCurrency= new ValidateCurrency();

    @Autowired
    private IPaymentDAO paymentDAO;

    @Override
    public boolean sendPayment(Payment payment) {
        log.info("Sending event by Kafka");
        try {
            producer.sendMessage(payment);
        }catch (Exception ex){
            return false;
        }
        return true;
    }

    public boolean isValidPayment(Payment payment) {
        return validatePayment.isValid(payment);
    }

    public boolean isValidCurrency(Payment payment) {
        return validateCurrency.isValid(payment);
    }

    public boolean isValidAmount(Payment payment) {
        return validateAmount.isValid(payment);
    }

    @Override
    public PaymentDTO getPaymentById(String id) {
        if(paymentDAO.findById(id).isPresent())
            return paymentDAO.findById(id).get();
        else
            return new PaymentDTO();
    }

    @Override
    public ArrayList<PaymentDTO> getAllPaymentsFromDb() {
        return (ArrayList<PaymentDTO>) paymentDAO.findAll();
    }

    @Override
    public void deletePaymentById(String id){
        log.info("Delete Payment with Id : {} ",id);
        paymentDAO.deleteById(id);
    }

    @Override
    public void deleteAll(){
        log.info("Delete All Payments from DB");
        paymentDAO.deleteAll();
    }
}
