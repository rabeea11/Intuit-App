package com.example.IntuitApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentMethod {
    private String paymentMethodId;
    private String paymentMethodName;
}
