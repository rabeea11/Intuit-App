package com.example.IntuitApp.services;

import com.example.IntuitApp.Interfaces.IPaymentDAO;
import com.example.IntuitApp.Interfaces.IPaymentMethodService;
import com.example.IntuitApp.model.Constants;
import com.example.IntuitApp.model.PaymentDTO;
import com.example.IntuitApp.model.PaymentMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class PaymentMethodService implements IPaymentMethodService {

    @Autowired
    IPaymentDAO paymentDAO;

    @Override
    public ArrayList<PaymentMethod> getAllPaymentMethodsFromDb() {
        ArrayList<PaymentMethod> methods = new ArrayList<>();
        for(PaymentDTO payment: paymentDAO.findAll()){
            methods.add(getPaymentMethodById(payment.getPaymentmethodid()));
        }
        return methods;
    }

    private PaymentMethod getPaymentMethodById(String methodId) {
        return new PaymentMethod(methodId, Constants.VISA_CARD);
    }

}
