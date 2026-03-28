package com.bankapp.models;

public abstract class Account {
    protected String accountId;
    protected double balance;

    public Account(String accountId, double balance) {
        this.accountId = accountId;
        this.balance = balance;
    }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public String getAccountId() {
        return accountId;
    }

    public double getBalance() {
        return balance;
    }
}
