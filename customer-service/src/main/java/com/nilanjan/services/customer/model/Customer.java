package com.nilanjan.services.customer.model;

/**
 * @author Nilanjan Roy
 */
public class Customer {

    private Long id;
    private String number;
    private int balance;
    private Long customerId;

    public Customer(String number, int balance, Long customerId) {
        this.number = number;
        this.balance = balance;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }
}
