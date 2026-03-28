package com.bankapp.services;

import com.bankapp.models.*;
import com.bankapp.utils.FileHandler;

import java.util.*;

public class BankService {

    private Scanner sc = new Scanner(System.in);
    private Map<String, Account> accounts = new HashMap<>();

    public void createAccount() {
        System.out.print("Enter Account ID: ");
        String id = sc.next();

        System.out.print("Initial Balance: ");
        double balance = sc.nextDouble();

        System.out.print("Type (1-Savings, 2-Current): ");
        int type = sc.nextInt();

        Account acc;

        if (type == 1) {
            acc = new SavingsAccount(id, balance);
        } else {
            acc = new CurrentAccount(id, balance);
        }

        accounts.put(id, acc);
        FileHandler.write("data/accounts.txt", id + "," + balance);

        System.out.println("Account created successfully!");
    }

    public void deposit() {
        System.out.print("Account ID: ");
        String id = sc.next();

        Account acc = accounts.get(id);
        if (acc == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        acc.deposit(amt);
        FileHandler.write("data/transactions.txt", "Deposit," + amt);

        System.out.println("Deposit successful!");
    }

    public void withdraw() {
        System.out.print("Account ID: ");
        String id = sc.next();

        Account acc = accounts.get(id);
        if (acc == null) {
            System.out.println("Account not found!");
            return;
        }

        System.out.print("Amount: ");
        double amt = sc.nextDouble();

        acc.withdraw(amt);
        FileHandler.write("data/transactions.txt", "Withdraw," + amt);

        System.out.println("Withdraw processed!");
    }

    public void showAllAccounts() {
        for (Account acc : accounts.values()) {
            System.out.println(acc.getAccountId() + " → Balance: " + acc.getBalance());
        }
    }
}
