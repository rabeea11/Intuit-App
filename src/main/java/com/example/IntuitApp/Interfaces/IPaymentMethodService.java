package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.PaymentMethod;

import java.util.ArrayList;

public interface IPaymentMethodService {
    ArrayList<PaymentMethod> getAllPaymentMethodsFromDb() ;
}
