package com.example.IntuitApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Payment {
    private long amount;
    private String currency;
    private String userId = String.valueOf(UUID.randomUUID());
    private String payeeId = String.valueOf(UUID.randomUUID());
    private String paymentMethodId = String.valueOf(UUID.randomUUID());
}
