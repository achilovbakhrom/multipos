package com.jim.multipos.Entity;


public class Vendor {
    private String name;
    private String contactName;
    private String email;
    private String phoneNumber;
    private String address;
    private double taxRate;
    private boolean isActive;

    public Vendor(String name, String contactName, String email,
                  String phoneNumber, String address, double taxRate, boolean isActive) {
        this.name = name;
        this.contactName = contactName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.taxRate = taxRate;
        this.isActive = isActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
