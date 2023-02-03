package com.example;

import java.util.List;

public class BankServiceSpy implements BankService{
    private  String id;
    private double amount;
    @Override
    public void pay(String id, double amount) {
    }

}
