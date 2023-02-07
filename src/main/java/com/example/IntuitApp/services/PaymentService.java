package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPaymentDAO;
import com.example.IntuitApp.Interfaces.IPaymentService;
import com.example.IntuitApp.kafka.Producer;
import com.example.IntuitApp.model.Payment;
import com.example.IntuitApp.model.PaymentDTO;
import com.example.IntuitApp.valdiators.ValidateAmount;
import com.example.IntuitApp.valdiators.ValidateCurrency;
import com.example.IntuitApp.valdiators.ValidatePayment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Override
    public ArrayList<String> getAllPaymentMethodsFromDb() {
        ArrayList<String> methods = new ArrayList<>();
        for(PaymentDTO payment: paymentDAO.findAll()){
            methods.add(payment.getPaymentmethodid());
        }
        return methods;
    }

    @Override
    public ArrayList<String> getAllPayeesFromDb() {
        ArrayList<String> methods = new ArrayList<>();
        for(PaymentDTO payment: paymentDAO.findAll()){
            methods.add(payment.getPayeeid());
        }
        return methods;
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
