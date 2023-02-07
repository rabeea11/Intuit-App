package com.example.IntuitApp.valdiators;

import com.example.IntuitApp.Interfaces.Validator;
import com.example.IntuitApp.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class ValidatePayment implements Validator {

    @Override
    public boolean isValid(Payment payment) {
        try {
            if(payment.getPaymentMethodId().isEmpty() || payment.getCurrency().isEmpty() || payment.getUserId().isEmpty() || payment.getPayeeId().isEmpty())
                return false;
        }catch (Exception e){
            return false;
        }
        return true;
    }
}
