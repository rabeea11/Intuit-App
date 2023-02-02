package com.example.IntuitApp.valdiators;

import com.example.IntuitApp.Interfaces.Validator;
import com.example.IntuitApp.model.Currency;
import com.example.IntuitApp.model.Payment;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class ValidateCurrency implements Validator {
    @Override
    public boolean isValid(Payment payment) {
        for(Currency cur : Currency.values()){
            if(payment.getCurrency().equalsIgnoreCase(cur.name()))
                return true;
        }
        return false;
    }
}
