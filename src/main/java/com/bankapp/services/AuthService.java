package com.bankapp.services;

public class AuthService {

    public boolean login(String user, String pass) {
        return user.equals("admin") && pass.equals("1234");
    }
}
