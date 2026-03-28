package com.bankapp.models;

public class CurrentAccount extends Account {

    private double overdraft = 10000;

    public CurrentAccount(String id, double balance) {
        super(id, balance);
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
    }

    @Override
    public void withdraw(double amount) {
        if (balance + overdraft < amount) {
            System.out.println("Overdraft limit exceeded!");
            return;
        }
        balance -= amount;
    }
}
