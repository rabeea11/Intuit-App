package com.example.IntuitApp.valdiators;

import com.example.IntuitApp.Interfaces.Validator;
import com.example.IntuitApp.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class ValidateAmount implements Validator {
    @Override
    public boolean isValid(Payment payment) {
        try {
            if(payment.getAmount() <= 0)
                 return false;
        }
        catch (Exception ex){
            return false;
        }
        return true;
    }
}
