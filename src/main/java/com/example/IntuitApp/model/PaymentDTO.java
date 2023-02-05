package com.example.IntuitApp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Data
@Table(name="payments")
public class PaymentDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private String paymentid;
    private long amount;
    private String currency;
    private String userid;
    private String payeeid;
    private String paymentmethodid;
    private int riskscore;
    private boolean allowed;
}
