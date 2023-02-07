package com.example.IntuitApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
public class PaymentMethod {
    private String paymentMethodId;
    private String paymentMethodName;
}
