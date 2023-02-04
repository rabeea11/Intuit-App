package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.PaymentDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface IPaymentDAO extends CrudRepository<PaymentDTO,String> {

    }
