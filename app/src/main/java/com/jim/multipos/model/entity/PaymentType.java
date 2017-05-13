package com.jim.multipos.model.entity;


public class PaymentType {
    private String name, accountId;
    public PaymentType(String name, String accountId) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAccountId() { return accountId; }
    public void setAccountId(String accountId) { this.accountId = accountId; }
}
