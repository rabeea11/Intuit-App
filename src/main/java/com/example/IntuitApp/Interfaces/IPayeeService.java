package com.example.IntuitApp.Interfaces;

import com.example.IntuitApp.model.Payee;

import java.util.ArrayList;

public interface IPayeeService {

    ArrayList<Payee> getAllPayeesFromDb();
}
