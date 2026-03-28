package com.bankapp.models;

public class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String toString() {
        return id + "," + name;
    }
}
