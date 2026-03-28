package com.bankapp;

import com.bankapp.services.BankService;
import com.bankapp.utils.ConsoleUI;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BankService bankService = new BankService();
        ConsoleUI ui = new ConsoleUI();

        while (true) {
            ui.showMainMenu();

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    bankService.createAccount();
                    break;
                case 2:
                    bankService.deposit();
                    break;
                case 3:
                    bankService.withdraw();
                    break;
                case 4:
                    bankService.showAllAccounts();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
