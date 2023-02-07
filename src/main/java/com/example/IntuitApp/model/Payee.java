package com.example.IntuitApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.kafka.common.protocol.types.Field;

@Data
@AllArgsConstructor
public class Payee {
    private String payeeId;
    private String firstName;
    private String lastName;
    private String email;
}
