package com.example.IntuitApp.valdiators;

import com.example.IntuitApp.Interfaces.Validator;
import com.example.IntuitApp.model.Payment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ValidatePayment implements Validator {

    @Override
    public boolean isValid(Payment payment) {
        if(payment.getPaymentMethodId().isEmpty() || payment.getCurrency().isEmpty() || payment.getUserId().isEmpty() || payment.getPayeeId().isEmpty())
            return false;
        return true;
    }
}
