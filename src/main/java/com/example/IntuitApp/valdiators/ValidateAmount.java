package com.example.IntuitApp.valdiators;

import com.example.IntuitApp.Interfaces.Validator;
import com.example.IntuitApp.model.Payment;
import org.springframework.stereotype.Service;

@Service
public class ValidateAmount implements Validator {
    @Override
    public boolean isValid(Payment payment) {
        if(payment.getAmount() <= 0)
            return false;
        return true;
    }
}
