package com.monitise.alkin.model;

public class LoginRequest {
    private String customerNo;
    private String password;

    public String getCustomerNo() {
        return customerNo;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest() {
    }

    public LoginRequest(String customerNo, String password) {
        this.customerNo = customerNo;
        this.password = password;
    }
}
